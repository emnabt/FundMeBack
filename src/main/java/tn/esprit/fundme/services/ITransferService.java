package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Transfer;

public interface ITransferService {
	
List<Transfer> retrieveAllTransfer();

Transfer retrieveTransfer(Long id);

public Transfer addTransaction(Transfer s );

List<Transfer> retrieveAllTransferByRib(Long idRib) ;
	
}

