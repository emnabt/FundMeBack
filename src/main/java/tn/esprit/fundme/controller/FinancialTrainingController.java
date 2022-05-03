package tn.esprit.fundme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.iap.Response;

import tn.esprit.fundme.entities.DomainClient;
import tn.esprit.fundme.entities.FinancialTraining;
import tn.esprit.fundme.repositories.FinancialTrainingRepository;
import tn.esprit.fundme.services.IFinancialTrainingService;
import tn.esprit.fundme.services.IUserService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/financialtraining")
public class FinancialTrainingController {
	
	@Autowired
	IFinancialTrainingService financialtrainingservice ;
	@Autowired
	FinancialTrainingRepository financialtrainingrepository ;
	
	@Autowired
	IUserService userservice ;
	@Autowired
	ServletContext context ;
	
	//http://localhost:8083/springMVC/financialtraining/retrieve-all-financialtrainings
		@GetMapping("/retrieve-all-financialtrainings")
		@ResponseBody
		public List<FinancialTraining>getFinancialTrainings(){
			List<FinancialTraining>listFinancialTrainings = financialtrainingservice.retrieveAllFinancialTrainings();
			return listFinancialTrainings;
		}
		@GetMapping("/getbydomain/{x}")
		@ResponseBody
		public List<FinancialTraining>getbydomain(@PathVariable("x") DomainClient d ){
			List<FinancialTraining>listFinancialTrainings = financialtrainingservice.retrieveAllFinancialTrainingsByDomain(d);
 			return listFinancialTrainings;
		}
		
		// http://localhost:8089/SpringMVC/financialtraining/retrieve-financialtraining/1
		@GetMapping("/retrieve-financialtraining/{financialtraining-id}")
		@ResponseBody
		public FinancialTraining retrieveFinancialTraining(@PathVariable("financialtraining-id") Long financialtrainingId) {
		return financialtrainingservice.retrieveFinancialTraining(financialtrainingId);
		}

		
		// http://localhost:8089/SpringMVC/financialtraining/add-financialtraining
		@PostMapping("/add")
		@ResponseBody
		public FinancialTraining addFinancialTraining(@RequestBody FinancialTraining t ,  Long User_id)
		{
			FinancialTraining financialtraining = financialtrainingservice.addFinancialTraining(t);
		return financialtraining;

		}
		// http://localhost:8089/SpringMVC/FinancialTraining/remove-financialtraining/{financialtraining-id}
		@DeleteMapping("/remove-financialtraining/{financialtraining-id}")
		@ResponseBody
		public void removeFinancialTraining(@PathVariable("financialtraining-id") Long financialtrainingId) {
			financialtrainingservice.deleteFinancialTraining(financialtrainingId);
		}

		// http://localhost:8089/SpringMVC/financialtraining/modify-financialtraining
		@PutMapping("/modify")
		@ResponseBody
		public FinancialTraining modifyFinancialTraining(@RequestBody FinancialTraining financialtraining ,  @PathVariable("training-id") Long idTraining) {
		return financialtrainingservice.updateFinancialTraining(financialtraining,idTraining);
		}
		
		
		////////////////////////////////////////////////////////////////////////////
		@GetMapping ("/getall")
		 public ResponseEntity<List<String>>getAll()
		{
			List<String> listFinanTrain = new ArrayList<String>();
			String filesPath = context.getRealPath("/Images") ;
			File filefolder = new File(filesPath) ;
			if (filefolder != null)
			{
				for (File file : filefolder.listFiles())
				{
					if(!file.isDirectory())
					{
						String encodeBase64 = null ;
					try {
						String extension = FileNameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int)file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						listFinanTrain.add("data:image/"+extension+";base64,"+encodeBase64);
						fileInputStream.close();
						
					}catch (Exception e) { 
					}
				}
			}
			}
				return new ResponseEntity<List<String>>(listFinanTrain,HttpStatus.OK ) ;
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		@PostMapping("/formation")
		public ResponseEntity<Response> createFinancialTraining (@RequestParam("file") MultipartFile file,
				@RequestParam("financialtraining") String financialtraining) throws JsonParseException , JsonMappingException , Exception 
{
	FinancialTraining finantrain = new ObjectMapper().readValue(financialtraining, FinancialTraining.class);
	boolean isExist = new File (context.getRealPath("/Images/")).exists();
	if(!isExist) 
	{
		new File (context.getRealPath("Images/")).mkdir() ;
	}
	String filename = file.getOriginalFilename();
	String newFileName = FileNameUtils.getBaseName(filename)+"."+FileNameUtils.getExtension(filename);
	File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
	try
	{
		FileUtils.writeByteArrayToFile(serverFile , file.getBytes()) ;
	}catch(Exception e) {
		e.printStackTrace(); 
	}
		
		
		finantrain.setImageTrainer(newFileName) ;
		FinancialTraining ft = financialtrainingrepository.save(finantrain) ;
		if (ft != null) {
			return new ResponseEntity<Response>(new Response ("") , HttpStatus.OK) ;
		}
		else
		{
			return new ResponseEntity<Response>(new Response ("Training not saved ") , HttpStatus.BAD_REQUEST);
		}
}
		/////////////////////////////getimagebyid//////////////////////////////////////////
		@GetMapping(path="ImageTrainer/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
			FinancialTraining ft = financialtrainingservice.retrieveFinancialTraining(id) ;
			return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+ft.getImageTrainer()));
		}
		
		
}
