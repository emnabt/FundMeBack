package tn.esprit.fundme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Deposit;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long>{

}