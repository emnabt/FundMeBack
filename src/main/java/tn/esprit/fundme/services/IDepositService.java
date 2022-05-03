package tn.esprit.fundme.services;

import java.util.List;


import tn.esprit.fundme.entities.Deposit;

public interface IDepositService {
	
List<Deposit> retrieveAllDeposit();

Deposit addDeposit(Deposit s);

void deleteDeposit(Long id);

Deposit updateDeposit(Deposit u);

Deposit retrieveDeposit(Long id);


public void assignDepositToUser(Long idDeposit, Long idUser);

	

}

