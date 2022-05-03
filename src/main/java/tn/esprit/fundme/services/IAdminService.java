package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Admin;
import tn.esprit.fundme.entities.Agent;

public interface IAdminService {
	
	List<Admin> retrieveAllAdmins();

	Admin addAdmin (Admin a);

	Admin updateAdmin (Admin a);

	Admin retrieveAdmin (Long id);
	// Admin retrieveAgent(Long id) ;

	void removeAdmin (Long id);
	public void assignAgentToAdmin(Long AdminId, Long AgentId);
}
