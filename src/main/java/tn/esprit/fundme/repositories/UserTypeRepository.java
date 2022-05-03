package tn.esprit.fundme.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{
	

	    @Query(" select u from UserType u " +
	            " where u.username = ?1")
	    UserType findUserWithName(String username);

	    @Query(" select u from UserType u " +
	            " where u.password = ?1")
	    UserType findUserWithMdp(String password);
	    
	   
	
}

