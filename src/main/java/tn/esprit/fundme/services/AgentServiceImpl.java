package tn.esprit.fundme.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import tn.esprit.fundme.elyessms.SmsSender;
import tn.esprit.fundme.entities.Admin;
import tn.esprit.fundme.entities.Agent;
import tn.esprit.fundme.entities.Role;
import tn.esprit.fundme.entities.UserType;
import tn.esprit.fundme.repositories.AdminRepository;
import tn.esprit.fundme.repositories.AgentRepository;
import tn.esprit.fundme.repositories.UserTypeRepository;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired(required=false)
	UserTypeRepository logrepository;
	@Autowired(required=false)
	AgentRepository agentrepository; 
	@Autowired(required=false)
	AdminRepository adminrepository; 
	@Autowired
	private MailService mailService;
	@Autowired
	private SmsSender serviceSms; 
	
	
	@Override
	public List<Agent> retrieveAllAgents() {
		return 	 (List<Agent>) agentrepository.findAll();

	}
	
	
	@Override
	public List<Agent> retrieveAllAgentsByAdmin(Long idAdmin) {
		List<Agent> lfa=agentrepository.getAgentsbyAdmin(idAdmin);
		
		for (Agent f: lfa) {
			System.out.println("Agents :" + f);
		}
		return lfa;
	}
	
	
	
	private UserType saveLog(Agent a){
		UserType log=a.getLogAgent();
		UserType x = logrepository.findUserWithName(log.getUsername());
		
		if(x==null)
		{
		log.setAgent(a);
		log.setRole(Role.AGENT);
		log.getAgent().setIdAgent(a.getIdAgent());
		logrepository.save(log);
		System.out.println("weeee");
		return log;
		}
		else {
			System.out.println("leeena");

			return null;
		}
	}
	
	@Override
	@Transactional
	public Agent addAgent(Agent s) {
		UserType log=null;
		log=saveLog(s);
		
		s.setLogAgent(log);
		Admin a = adminrepository.findById(s.getAdmins().getIdAdmin()).orElse(null);
		Set<Agent> lsa=a.getListAgents();
		
		
		
		
		if(a!=null && log!=null) {
			
			lsa.add(s);
            a.setListAgents(lsa);
            s.getLogAgent().setEmail("elyes.ghrairi@esprit.tn");
            
//serviceSms.sendSmsmsg("HEY");
    		try {
    			mailService.sendEmail(s);
    		} 
    		catch (MailException | MessagingException mailException) {
    			System.out.println(mailException);
    			} 
		catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			agentrepository.save(s);

		}
		if(log==null) System.out.print("USERNAME EXISTANT ! ");
			
			return s;
	}

	@Override
	public Agent updateAgent(Agent u) {
		UserType log = logrepository.findUserWithName(u.getLogAgent().getUsername());
		log.setAgent(u);
		logrepository.save(log);	
		agentrepository.save(u);
		
		return u;
	}

	@Override
	public Agent retrieveAgent(Long id) {
		return  agentrepository.findById(id).orElse(null);

	}

	@Override
	public void removeAgent(Long id) {
		Agent a = agentrepository.findById(id).orElse(null);

		agentrepository.deleteById(id);		
		logrepository.delete(a.getLogAgent());

		}

	
}
