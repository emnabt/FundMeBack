package tn.esprit.fundme.controller;

import java.util.List;

import javax.batch.operations.JobExecutionAlreadyCompleteException;
import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.batch.BatchLauncher;
import tn.esprit.fundme.entities.Supplier;
 import tn.esprit.fundme.services.SupplierService;

@RestController
@RequestMapping("supplier")
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	@GetMapping("/select")
	@ResponseBody
	public List<Supplier>getsuppliers(){
		List<Supplier>suppliers =  supplierService.retrieveAllSupplier();
		return suppliers;
	}
	@GetMapping("/select/{id}")
	@ResponseBody
	public Supplier getSupplier(@PathVariable("id") Long Id){
 
		 Supplier f = supplierService.retrieveSupplier(Id);
		return f;
	}
}
