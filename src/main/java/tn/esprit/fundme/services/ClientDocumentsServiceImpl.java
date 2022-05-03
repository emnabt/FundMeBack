package tn.esprit.fundme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.ClientDocuments;
import tn.esprit.fundme.repositories.ClientDocumentsRepository;

@Service
public class ClientDocumentsServiceImpl implements IClientDocumentsService{

       @Autowired(required=false)
	ClientDocumentsRepository cdrepo; 
	
	@Override
	public List<ClientDocuments> retrieveAllUsers() {
		return 	 (List<ClientDocuments>) cdrepo.findAll();

	}

	@Override
	public ClientDocuments addUser(ClientDocuments s) {
		cdrepo.save(s);
		
		return s;
	}

	@Override
	public ClientDocuments updateUser(ClientDocuments u) {
cdrepo.save(u);
		
		return u;
	}

	@Override
	public ClientDocuments retrieveUser(Long id) {
		return  cdrepo.findById(id).orElse(null);

	}

	@Override
	public void removeUser(Long id) {
		cdrepo.deleteById(id);		
		
	}

}
