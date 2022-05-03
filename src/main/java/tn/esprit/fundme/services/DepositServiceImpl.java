package tn.esprit.fundme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.Deposit;
import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.repositories.DepositRepository;
import tn.esprit.fundme.repositories.UserRepository;


@Service
public class DepositServiceImpl implements IDepositService{

	@Autowired
	DepositRepository depositRepository; 
	@Autowired
	UserRepository userRepository; 
	

	@Override
	public List<Deposit> retrieveAllDeposit() {
		return (List<Deposit>) depositRepository.findAll();
	}

	@Override
	public Deposit addDeposit(Deposit s) {
		return depositRepository.save(s);	
		 }

	@Override
	public void deleteDeposit(Long id) {
		depositRepository.deleteById(id);		
	}

	@Override
	public Deposit updateDeposit(Deposit u) {
		depositRepository.save(u)	;
		return u;
	}

	@Override
	public Deposit retrieveDeposit(Long id) {
		 return depositRepository.findById(id).orElse(null);	
	}

	@Override
	public void assignDepositToUser(Long idDeposit, Long idUser) {
	
			User s = userRepository.findById(idUser).orElse(null);
			Deposit p=depositRepository.findById(idDeposit).orElse(null);
			p.setUsers_Deposit(s);
			depositRepository.save(p);
			
		
		
	}

}
