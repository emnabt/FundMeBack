package tn.esprit.fundme.controller;


import java.util.List;

import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.Deposit;
import tn.esprit.fundme.entities.Transfer;
import tn.esprit.fundme.services.ITransferService;
import tn.esprit.fundme.services.TransferServiceImpl;



@RestController
@RequestMapping("/transfer")
public class TransferController {

@Autowired
ITransferService TransferServiceImpl;

// http://localhost:8086/SpringMVC/transfer/retrieve-all-stocks
@GetMapping("/retrieve-all-stocks")
@ResponseBody
public List<Transfer> getStocks() {
List<Transfer> listTransfer=TransferServiceImpl.retrieveAllTransfer();
return listTransfer;
}

//http://localhost:8086/SpringMVC/transfer/retrieve-fact/four-id}"
	@GetMapping("/retrieve-fact/{rib}")
	@ResponseBody
	public List<Transfer>  gettranbyribr(@PathVariable("rib") Long idFournisseur) {
		List<Transfer> tr=TransferServiceImpl.retrieveAllTransferByRib(idFournisseur);
		return tr ;
	}
//http://localhost:8086/SpringMVC/transfer/add
	@PostMapping("/add")
	@ResponseBody
	public Transfer addTransfer(@RequestBody Transfer s) {
	Transfer transfer = TransferServiceImpl.addTransaction(s);
	return transfer;
		}
	
	
	
}