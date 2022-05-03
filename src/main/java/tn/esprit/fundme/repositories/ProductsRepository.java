package tn.esprit.fundme.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Product;
@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {

	
	Optional<Product> findByType(String type);
	
	@Query("Select p from Product p where p.quantity = 0")
	List<Product> checkQuantity();

}
