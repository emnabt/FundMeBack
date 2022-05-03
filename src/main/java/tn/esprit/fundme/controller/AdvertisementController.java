package tn.esprit.fundme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.*;
import tn.esprit.fundme.services.*;

@RestController
@RequestMapping("/Advertisement")
public class AdvertisementController {

	@Autowired
	IAdvertisementService advertisementService;
	
	// http://localhost:8089/SpringMVC/Advertisement/retrieve-all-Advertisements
	
	@GetMapping("/afficher")
	@ResponseBody
	public List<Advertisement> getAdvertisements() {
	List<Advertisement> listAdvertisements = advertisementService.retrieveAllAdvertisements();
	return listAdvertisements;
	}
	
	@GetMapping("/afficher/{Advertisement-id}")
	@ResponseBody
	public Advertisement retrieveAdvertisement(@PathVariable("Advertisement-id") Long AdvertisementId) {
	return advertisementService.retrieveAdvertisement(AdvertisementId);
	}
	
	
	// http://localhost:8089/SpringMVC/Advertisement/add-Advertisement
	@PostMapping("/ajouter")
	@ResponseBody
	public Advertisement addAdvertisement(@RequestBody Advertisement o)
	{
		Advertisement Advertisement = advertisementService.addAdvertisement(o);
	return Advertisement;
	}
	
	
	// http://localhost:8089/SpringMVC/Advertisement/remove-Advertisement/{Advertisement-id}
	@DeleteMapping("/supprimer/{Advertisement-id}")
	@ResponseBody
	public void removeAdvertisement(@PathVariable("Advertisement-id") Long AdvertisementId) {
		advertisementService.removeAdvertisement(AdvertisementId);
	}
	// http://localhost:8089/SpringMVC/Advertisement/modify-Advertisement
	@PutMapping("/modifier")
	@ResponseBody
	public Advertisement modifyAdvertisement(@RequestBody Advertisement Advertisement) {
	return advertisementService.updateAdvertisement(Advertisement);
	}
	
	
	
}
