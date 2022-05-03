package tn.esprit.fundme.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.fundme.entities.DomainClient;
import tn.esprit.fundme.entities.FinancialTraining;

@Repository
public interface FinancialTrainingRepository  extends CrudRepository<FinancialTraining , Long>{

 	List<FinancialTraining> findByUsers_Domain(DomainClient domainclient);
}
