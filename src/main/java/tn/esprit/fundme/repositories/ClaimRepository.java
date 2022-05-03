package tn.esprit.fundme.repositories;


//import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Claim;
import tn.esprit.fundme.entities.DictionnaireDesMotsInterdits;


@Repository
public interface ClaimRepository extends CrudRepository<Claim,Long> {
	
	
	@Query("SELECT mots FROM DictionnaireDesMotsInterdits mots")
	List<DictionnaireDesMotsInterdits> Dictionnaire();
	
	@Query("SELECT c FROM Claim c WHERE c.Product_claim.product_id = product")
	List<Claim> getClaimsByProduit(Long product);
	
	
   // @Query("SELECT c FROM Claim c WHERE ID_Credit= ID")
	//List<Claim> getClaimsByCredit(Long ID);
    

	@Query("SELECT c FROM Claim c WHERE c.financialtraining_claim.idTraining= idTraining")
	List<Claim> getClaimsByFinancialTraining(Long idTraining);
	


}
