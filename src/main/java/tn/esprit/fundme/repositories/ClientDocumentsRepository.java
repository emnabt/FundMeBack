package tn.esprit.fundme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.ClientDocuments;

@Repository
public interface ClientDocumentsRepository extends CrudRepository<ClientDocuments, Long>{

}
