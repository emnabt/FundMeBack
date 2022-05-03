package tn.esprit.fundme.services;

import java.util.List;


import tn.esprit.fundme.entities.DomainClient;
import tn.esprit.fundme.entities.FinancialTraining;
import tn.esprit.fundme.entities.LikeTs;


public interface IFinancialTrainingService {

	List<FinancialTraining> retrieveAllFinancialTrainings();

	FinancialTraining addFinancialTraining(FinancialTraining t);

	void deleteFinancialTraining(Long id);

	FinancialTraining updateFinancialTraining(FinancialTraining t ,Long idTraining);

	FinancialTraining retrieveFinancialTraining(Long id);
	
	
	List<FinancialTraining> retrieveAllFinancialTrainingsByDomain(DomainClient domain);
	
	public void AddLikesToFinancialTraining(LikeTs likets,Long id_User,Long idTraining);
	
	

	
	
	
	
}
