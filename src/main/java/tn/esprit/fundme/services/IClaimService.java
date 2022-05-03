package tn.esprit.fundme.services;


import java.util.List;

import tn.esprit.fundme.entities.Claim;

public interface IClaimService {

	List<Claim> retrieveAllClaims();

	Claim addClaim(Claim c , Long id_User);

	void deleteClaim(Long id);

	Claim updateClaim(Claim c, Long id_Claim );

	Claim retrieveClaim(Long id_Claim);
	
	void assignClaimToProduct(Long product_id, Long id_Claim); 
	
	//void assignClaimToCredit(Long ID, Long id_Claim);
	
	void assignClaimToFinancialTraining(Long idTraining, Long id_Claim);
	
	List<Claim> retrieveAllClaimsByProduct(Long product_id) ;
	
	//List<Claim> retrieveAllClaimsByCredit(Long ID) ;
	
	List<Claim> retrieveAllClaimsByFinancialTraining(Long idTraining) ;
	
	
	//boolean checkWord(String description) ;
	
	String checkmots(Claim c , Long id);
	
	

	
}
