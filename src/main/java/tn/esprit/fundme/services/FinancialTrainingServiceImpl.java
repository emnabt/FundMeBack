package tn.esprit.fundme.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundme.entities.DomainClient;
import tn.esprit.fundme.entities.FinancialTraining;
import tn.esprit.fundme.entities.InteractionTs;
import tn.esprit.fundme.entities.LikeTs;
import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.repositories.FinancialTrainingRepository;
import tn.esprit.fundme.repositories.LikeTsRepository;
import tn.esprit.fundme.repositories.UserRepository;

@Service
public class FinancialTrainingServiceImpl implements IFinancialTrainingService {

	@Autowired
	FinancialTrainingRepository financialtrainingrepository ;
	@Autowired
	UserRepository userrepository ;
	@Autowired(required=false)
	LikeTsRepository liketsrepository ;
	
	@Override
	public List<FinancialTraining> retrieveAllFinancialTrainings() {
		return (List<FinancialTraining>) financialtrainingrepository.findAll();
	}

	@Override
	public FinancialTraining addFinancialTraining(FinancialTraining t) {
		financialtrainingrepository.save(t);
		 
		return t ;
	}

	@Override
	public void deleteFinancialTraining(Long id) {
		financialtrainingrepository.deleteById(id);
	}

	@Override
	public FinancialTraining updateFinancialTraining(FinancialTraining t , Long idTraining) {
		FinancialTraining formationModif = financialtrainingrepository.findById(idTraining).get(); 
		formationModif.setTrainer(t.getTrainer());
		formationModif.setDescriptionTrain(t.getDescriptionTrain());
		formationModif.setTypeTrain(t.getTypeTrain());
		formationModif.setDateTrain(t.getDateTrain());
		formationModif.setImageTrainer(t.getImageTrainer());
		financialtrainingrepository.save(t);
		return t ;
	}

	@Override
	public FinancialTraining retrieveFinancialTraining(Long id) {
		return financialtrainingrepository.findById(id).orElse(null);
	}
	
    
	
	@Override
	public List<FinancialTraining> retrieveAllFinancialTrainingsByDomain(DomainClient domainclient) {
		 
	    System.out.println(domainclient);
		return  financialtrainingrepository.findByUsers_Domain(domainclient);
	}
	
	@Override
	public void AddLikesToFinancialTraining(LikeTs likets,Long id_User,Long idTraining) {
		Iterable<User> user = userrepository.findAll();
		for (User user2 : user) {
			if (liketsrepository.LikesTs(idTraining, id_User)==0) {
				if (user2.getId_User()==id_User) {
					
					financialtrainingrepository.findById(idTraining).map(c ->{
						
						likets.setInteractionType(InteractionTs.Like);
						likets.setUser(user2);
						likets.setFt_like(c);
						likets.setLiked(true);
						return c;
						
					});
					
					liketsrepository.save(likets);
				}
				
			}
			
		}
		
	}

	

	

}

