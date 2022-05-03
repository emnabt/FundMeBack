package tn.esprit.fundme.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.fundme.entities.Admin;
import tn.esprit.fundme.entities.Agent;
import tn.esprit.fundme.entities.Role;
import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.entities.UserType;
import tn.esprit.fundme.repositories.AdminRepository;
import tn.esprit.fundme.repositories.AgentRepository;
import tn.esprit.fundme.repositories.UserTypeRepository;


@Service
public class AdminServiceImpl implements IAdminService {
	
	
	@Autowired(required=false)
	AgentRepository agrepo; 

	@Autowired(required=false)
	AdminRepository adminrepository; 
	@Autowired(required=false)
	AgentRepository agrepository;
	
	User u;
	
	@Autowired(required=false)
	UserTypeRepository logrepository;
	//@Autowired(required=false)
	
	
	@Override
	public List<Admin> retrieveAllAdmins() {
		
		return 	 (List<Admin>) adminrepository.findAll();

	}

	/*@Override
	public Admin addAdmin(Admin s) {
		
		  adminrepository.save(s);
			
			return s;
	}*/
	@Transactional
	public Admin addAdmin(Admin a) {	
		UserType log=null;
		log=saveLog(a);
		if(log!=null) {
			a.setLogAdmin(log);
		adminrepository.save(a);
		}
		else System.out.println("Desole Username deja existant !  ");
		return a;
	}
	
	private UserType saveLog(Admin a){
		UserType log=a.getLogAdmin();
		UserType x = logrepository.findUserWithName(log.getUsername());
		
		if(x==null)
		{
		log.setAdmin(a);
		log.setRole(Role.ADMIN);
		log.getAdmin().setIdAdmin(a.getIdAdmin());
		logrepository.save(log);
		return log;
		}
		else return null;
	}

	@Override
	public Admin updateAdmin(Admin u) {
		UserType log = logrepository.findUserWithName(u.getLogAdmin().getUsername());
		log.setAdmin(u);
		logrepository.save(log);	
		adminrepository.save(u);
		
		return u;
	}

	@Override
	public Admin retrieveAdmin(Long id) {
		return  adminrepository.findById(id).orElse(null);

	}

	@Override
	public void removeAdmin(Long id) {
		Admin a = adminrepository.findById(id).orElse(null);
		adminrepository.deleteById(id);		
		
		logrepository.delete(a.getLogAdmin());
	}
	
	/*@Override
	public Admin retrieveAgent(Long id) {
		RawDBDemoGeoIPLocationService map = null;
		try {
			map = new RawDBDemoGeoIPLocationService();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};

		try {
			map.getLocation("116.73.210.21");
		} catch (IOException | GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  adminrepository.findById(id).orElse(null);

	}
*/
	@Override
	public void assignAgentToAdmin(Long AdminId, Long AgentId) {
		Admin fourn=adminrepository.findById(AdminId).orElse(null);
		Agent sa=agrepo.findById(AgentId).orElse(null);
		
		Set<Agent> lsa=new HashSet<>();
		lsa.add(sa);
		fourn.setListAgents(lsa);
		adminrepository.save(fourn);	
	}
	
	
}





