package tn.esprit.fundme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.fundme.entities.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long>{
	@Query("SELECT t FROM Transfer t WHERE t.deposit.rib= :fourni")
	List<Transfer> getrib(@Param("fourni") Long transfert);

}