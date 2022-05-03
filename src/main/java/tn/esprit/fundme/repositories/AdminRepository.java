package tn.esprit.fundme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	@Query("FROM Admin e")
	List<Admin> getAllEmployeeData();

}
