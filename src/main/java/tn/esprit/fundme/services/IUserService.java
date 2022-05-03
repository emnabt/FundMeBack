package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.User;

public interface IUserService {

	
	List<User> retrieveAllUsers();

	User addUser (User a);

	User updateUser (User a);

	User retrieveUser (Long id);

	void removeUser (Long id);
	
	void assignUserToFinancialTraining(Long idTraining, Long id_User);
}
