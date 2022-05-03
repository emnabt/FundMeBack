package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Agent;

public interface IAgentService {
	
	List<Agent> retrieveAllAgents();

	Agent addAgent (Agent a);

	Agent updateAgent (Agent a);

	Agent retrieveAgent (Long id);

	void removeAgent (Long id);
	public List<Agent> retrieveAllAgentsByAdmin(Long idAdmin);
}
