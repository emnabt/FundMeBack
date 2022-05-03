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

import tn.esprit.fundme.entities.Deposit;
import tn.esprit.fundme.services.IDepositService;

@RestController
@RequestMapping("/Deposit")
public class DepositController {

	@Autowired
	IDepositService DepositServiceImpl;

// http://localhost:8086/SpringMVC/Deposit/retrieve-all
	@GetMapping("/retrieve-all")
	@ResponseBody
	public List<Deposit> getStocks() {
		List<Deposit> listDeposit = DepositServiceImpl.retrieveAllDeposit();
		return listDeposit;
	}

//http://localhost:8086/SpringMVC/Deposit/retrieve/1
	@GetMapping("/retrieve/{rib}")
	@ResponseBody
	public Deposit retrieveStock(@PathVariable("rib") Long depositId) {
		return DepositServiceImpl.retrieveDeposit(depositId);
	}

//http://localhost:8086/SpringMVC/Deposit/add
	@PostMapping("/add")
	@ResponseBody
	public Deposit addStock(@RequestBody Deposit s) {
		Deposit deposit = DepositServiceImpl.addDeposit(s);
		return deposit;
	}
	

//http://localhost:8086/SpringMVC/Deposit/modify
	@PutMapping("/modify")
	@ResponseBody
	public Deposit modifyOperateur(@RequestBody Deposit dep) {
		return DepositServiceImpl.updateDeposit(dep);
		
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void remove(@PathVariable("id")  Long id) {
		DepositServiceImpl.deleteDeposit(  id);
		}
	
	@PostMapping("/ass/{rib}/{user}")
	@ResponseBody
	public void assign(@PathVariable("rib") Long depositId,@PathVariable("user") Long userId) {
	  DepositServiceImpl.assignDepositToUser(depositId, userId);
		 
	}
}