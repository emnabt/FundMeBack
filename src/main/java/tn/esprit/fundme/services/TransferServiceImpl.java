package tn.esprit.fundme.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;


import tn.esprit.fundme.entities.Deposit;
import tn.esprit.fundme.entities.Transfer;
import tn.esprit.fundme.repositories.DepositRepository;
import tn.esprit.fundme.repositories.TransferRepository;
 import tn.esprit.fundme.exception.Exception;

@Service
public class TransferServiceImpl implements ITransferService{

	@Autowired
	TransferRepository transferRepository; 
	@Autowired
	DepositRepository depositRepository;
	@Autowired
	MailService notificationService;
	

	@Override
	public List<Transfer> retrieveAllTransfer() {
		return (List<Transfer>) transferRepository.findAll();
	}



	@Override
	public Transfer retrieveTransfer(Long id) {
		 return transferRepository.findById(id).orElse(null);	
	}
	
	
	@Override
	public List<Transfer> retrieveAllTransferByRib(Long idRib) {
		
		
		return (List<Transfer>) transferRepository.getrib(idRib);
	}



	@Transactional
	public Transfer addTransaction(Transfer s){
		    
    	Deposit dest =depositRepository.findById(s.getRibdest()).orElse(null) ;

	
		Deposit emet =depositRepository.findById(s.getDeposit().getRib()).orElse(null) ;

		if (s.getAmount()<=(emet.getBalance()))
		{
		    emet.setBalance(emet.getBalance()-s.getAmount());
		    
		    try {
		    	notificationService.sendEmailemet(emet.getUsers_Deposit());
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
		    System.out.println( "Congratulations! Your mail has been send to the user.");
		    
	        dest.setBalance(dest.getBalance()+s.getAmount());
	        try {
		    	notificationService.sendEmaildest(dest.getUsers_Deposit());
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
		    System.out.println( "Congratulations! Your mail has been send to the user.");
	        
			
		}
		else throw new Exception("insufficient fund..!");
			
		
		return transferRepository.save(s);
	}
	
    
	}
