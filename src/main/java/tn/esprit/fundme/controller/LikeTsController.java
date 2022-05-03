package tn.esprit.fundme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.LikeTs;
import tn.esprit.fundme.services.ILikeTsService;
import tn.esprit.fundme.services.LikeTsService;



@RestController
@RequestMapping("/LikeTs")
public class LikeTsController {
	
	@Autowired
	LikeTsService likeTsService ;
	@Autowired
	ILikeTsService iLikeTsService ;

	
	
	@PostMapping("/AddLikesToMyTs/{idUser}/{idTs}")
	@ResponseBody
	public void AddLikesToFinancialTraining(@RequestBody LikeTs likets,@PathVariable("idUser") Long id_User, @PathVariable("idTs") Long idTraining){
		iLikeTsService.AddLikesToFinancialTraining(likets, id_User, idTraining);
	}

	@PostMapping("/AddDislikeToTrainingSession/{idUser}/{idTs}")
	@ResponseBody
	public void AddDislikeToFinancialTraining(@RequestBody LikeTs likes, @PathVariable("idUser") Long id_User, @PathVariable("idTs") Long idTraining) {
		iLikeTsService.AddDislikeToFinancialTraining(likes, id_User, idTraining);
	}
	
	@DeleteMapping("/Deletelike/{idUser}/{idTs}")
	public void Deletelike(@PathVariable("idUser") Long id_User, @PathVariable("idTs") Long idTraining){
		iLikeTsService.Deletelike(id_User, idTraining);
	}
	
	
	@DeleteMapping("/DeleteDislike/{idUser}/{idTs}")
	public void DeleteDislike(@PathVariable("idUser") Long id_User, @PathVariable("idTs") Long idTraining){
		iLikeTsService.DeleteDislike(id_User, idTraining);
	}

}
