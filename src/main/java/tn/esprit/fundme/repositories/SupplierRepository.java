package tn.esprit.fundme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Supplier;
@Repository
public interface SupplierRepository extends CrudRepository<Supplier , Long>{

}
