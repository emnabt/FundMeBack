package tn.esprit.fundme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.User;
@Repository
public interface UserRepository  extends CrudRepository<User, Long>{

}
