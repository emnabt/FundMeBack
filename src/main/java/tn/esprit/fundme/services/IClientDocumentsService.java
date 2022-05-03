package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.ClientDocuments;

public interface IClientDocumentsService {

	
	List<ClientDocuments> retrieveAllUsers();

	ClientDocuments addUser (ClientDocuments a);

	ClientDocuments updateUser (ClientDocuments a);

	ClientDocuments retrieveUser (Long id);

	void removeUser (Long id);
}
