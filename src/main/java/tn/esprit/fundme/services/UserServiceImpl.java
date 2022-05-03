package tn.esprit.fundme.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundme.entities.ClientDocuments;
import tn.esprit.fundme.entities.FinancialTraining;
import tn.esprit.fundme.entities.Role;
import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.entities.UserType;
import tn.esprit.fundme.repositories.ClientDocumentsRepository;
import tn.esprit.fundme.repositories.FinancialTrainingRepository;
import tn.esprit.fundme.repositories.UserRepository;
import tn.esprit.fundme.repositories.UserTypeRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired(required=false)
	UserTypeRepository logrepository;

	@Autowired(required=false)
	UserRepository userrepository; 
	@Autowired(required=false)
	ClientDocumentsRepository docrepository;
	@Autowired
	FinancialTrainingRepository financialtrainingrepository ;
	
	
	@Override
	public List<User> retrieveAllUsers() {
		
		return 	 (List<User>) userrepository.findAll();
		

	}

	@Transactional
	public User addUser(User a) {
		UserType log=null;
		log=saveLog(a);
		
		ClientDocuments Cd=a.getUserDocuments();
		System.out.print(a.getUserDocuments().getPhone());
		docrepository.save(Cd);
		
		if(log!=null) {
			a.setLogUser(log);
			a.setUserDocuments(Cd);
			userrepository.save(a);
		}
		else System.out.println("Desole Username deja existant !  ");
		return a;

	}

	
	private UserType saveLog(User a){
		UserType log=a.getLogUser();
		UserType x = logrepository.findUserWithName(log.getUsername());
		
		if(x==null)
		{
		log.setUser(a);
		log.setRole(Role.USER);
		log.getUser().setId_User(a.getId_User());
		logrepository.save(log);
		return log;
		}
		else return null;
	}

	@Override
	public User updateUser(User u) {
		UserType log = logrepository.findUserWithName(u.getLogUser().getUsername());
		log.setUser(u);
		logrepository.save(log);			
		userrepository.save(u);
		
		return u;
	}

	@Override
	public User retrieveUser(Long id) {
		return  userrepository.findById(id).orElse(null);

	}

	@Override
	public void removeUser(Long id) {
		User a = userrepository.findById(id).orElse(null);

		userrepository.deleteById(id);	
		logrepository.delete(a.getLogUser());

	}

	@Override
	public void assignUserToFinancialTraining(Long idTraining, Long id_User) {
		FinancialTraining ft = financialtrainingrepository.findById(idTraining).orElse(null);
		User c = userrepository.findById(id_User).orElse(null);
		c.setFormations_user(ft);
		userrepository.save(c);
		
	}
}
