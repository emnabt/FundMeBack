package tn.esprit.fundme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.entities.*;
import tn.esprit.fundme.services.*;

@RestController
@RequestMapping("/Agent")
public class AgentController {

	@Autowired
	IAgentService agentService;
	
	


	
	
	// http://localhost:8089/SpringMVC/Agent/retrieve-all-Agents
	
	@GetMapping("/afficher")
	@ResponseBody
	public List<Agent> getAgents() {
		
	List<Agent> listAgents = agentService.retrieveAllAgents();
	return listAgents;
	}
	
	@GetMapping("/afficher/{Agent-id}")
	@ResponseBody
	public Agent retrieveAgent(@PathVariable("Agent-id") Long AgentId) {
	return agentService.retrieveAgent(AgentId);
	}
	
	@GetMapping("/afficherlisteagentadmin/{Admin-id}")
	@ResponseBody
	public List<Agent>  retrieveAllAgentsByAdmin(@PathVariable("Admin-id") Long AgentId) {
	return agentService.retrieveAllAgentsByAdmin(AgentId);
	}
	
	
	// http://localhost:8089/SpringMVC/Agent/add-Agent
	@PostMapping("/ajouter")
	@ResponseBody
	public Agent addAgent(@RequestBody Agent o)
	{
		Agent Agent = agentService.addAgent(o);		

	return Agent;
	}
	
	
	// http://localhost:8089/SpringMVC/Agent/remove-Agent/{Agent-id}
	@DeleteMapping("/supprimer/{Agent-id}")
	@ResponseBody
	public void removeAgent(@PathVariable("Agent-id") Long AgentId) {
		agentService.removeAgent(AgentId);
	
	}
	// http://localhost:8089/SpringMVC/Agent/modify-Agent
	@PutMapping("/modifier")
	@ResponseBody
	public Agent modifyAgent(@RequestBody Agent Agent) {
	return agentService.updateAgent(Agent);
	}
	
	
	
}
