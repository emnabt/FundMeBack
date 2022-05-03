package tn.esprit.fundme.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.fundme.entities.Claim;
import tn.esprit.fundme.repositories.ClaimRepository;
import tn.esprit.fundme.services.IClaimService;
 import tn.esprit.fundme.services.IFinancialTrainingService;
import tn.esprit.fundme.services.ProductsService;
 
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/Claim")
public class ClaimController {

	@Autowired
	IClaimService claimservice ;
	ProductsService productservice ;
	@Autowired
	IFinancialTrainingService financialtrainingservice ; 
	@Autowired
	ClaimRepository claimrepository ; 
	
	    //http://localhost:8093/Claim/retrieve-all-claims
		@GetMapping("/retrieve-all-claims")
		@ResponseBody
		public List<Claim>getClaims(){
			List<Claim>listClaims = claimservice.retrieveAllClaims();
			return listClaims;
		}
		
		// http://localhost:8088/SpringMVC/Claim/retrieve-Claim/1
		@GetMapping("/retrieve-Claims/{claim-id}")
		@ResponseBody
		public Claim retrieveClaim(@PathVariable("claim-id") Long id_Claim) {
		return claimservice.retrieveClaim(id_Claim);
		}

		
		// http://localhost:8088/SpringMVC/Claim/add-claim
		@PostMapping("/addclaim/{id}")
		@ResponseBody
		public Claim addClaim(@RequestBody Claim c  , @PathVariable("id") Long   User_id)
		{
			Claim claim = claimservice.addClaim(c  , User_id);
			return claim ;

		}
		// http://localhost:8088/SpringMVC/claim/remove-claim/{claim-id}
		@DeleteMapping("/remove-operateur/{claim-id}")
		@ResponseBody
		public void removeClaim(@PathVariable("claim-id") Long id_Claim) {
			claimservice.deleteClaim(id_Claim);
		}

		// http://localhost:8088/SpringMVC/claim/modify-claim
		@PutMapping("/modify-claims/{claim-id}")
		@ResponseBody
		public Claim modifyClaim(@RequestBody Claim claim, @PathVariable("claim-id") Long id_Claim) {
		return claimservice.updateClaim(claim, id_Claim);
		}
		
		//http://localhost:8088/SpringMVC/claim/assignproduct
		@PutMapping("/assignproduct/{claim-id}/{claim-produit}")
		@ResponseBody
		public void assignProduct_to_op(@PathVariable("claim-id") Long id_Claim ,@PathVariable("claim-produit") Long product_id ) {
               ((IClaimService) productservice).assignClaimToProduct(id_Claim,product_id);
				}
		/*
		//http://localhost:8088/SpringMVC/claim/assigncredit
		@PutMapping("/assigncredits/{claim-id}/{claim-credit}")
		@ResponseBody
		public void assignCredits_to_op(@PathVariable("claim-id") Long id_Claim ,@PathVariable("claim-credit") Long ID) {
		       ((IClaimService) creditservice).assignClaimToCredit(id_Claim,ID);
						}
		*/
		
		//http://localhost:8088/SpringMVC/claim/assignfinancialtraining
		@PutMapping("/assigncfinancialtraining/{claim-id}/{claim-financialtraining}")
		@ResponseBody
		public void assignFinancialTraining_to_op(@PathVariable("claim-id") Long id_Claim ,@PathVariable("claim-financialtraining") Long idTraining ) {
			   ((IClaimService) financialtrainingservice).assignClaimToFinancialTraining(id_Claim,idTraining);
								}
		
		
		//http://localhost:8088/springMVC/Claim/retrieve-all-claims-by-product
		@GetMapping("/retrieve-all-claims-by-product")
		@ResponseBody
	    public List<Claim> retriveProduct_claims(@PathVariable("id_product") Long product_id) {
	       List<Claim> listClaims = claimservice.retrieveAllClaimsByProduct(product_id);
	       return listClaims ;
	    }
		
		@GetMapping("/retrieve-all-claims-by-financialtraining")
		@ResponseBody
	    public List<Claim> retrieveClaimsByFinancialTrainings(@PathVariable("id_training") Long idTraining) {
	       List<Claim> listClaims = claimservice.retrieveAllClaimsByFinancialTraining(idTraining);
	       return listClaims ;
	    }
		
	
				
	    // http://localhost:8088/SpringMVC/Claim/check-Claim
		/*@GetMapping("/check-Claims/{descript}")
		@ResponseBody
		public boolean CheckClaim(@PathVariable("descript") String description) {
		return claimservice.checkWord(description);
				}*/
		
		@PostMapping("/add/{id}")
	     public	String CheckClaims(@PathVariable Long id, @RequestBody Claim c){
			return claimservice.checkmots(c , id) ;
		}
				
				
				
		
		
}
