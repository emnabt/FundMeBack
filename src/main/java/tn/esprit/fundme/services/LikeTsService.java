package tn.esprit.fundme.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.InteractionTs;
import tn.esprit.fundme.entities.LikeTs;
import tn.esprit.fundme.entities.User;
import tn.esprit.fundme.repositories.FinancialTrainingRepository;
import tn.esprit.fundme.repositories.LikeTsRepository;
import tn.esprit.fundme.repositories.UserRepository;


@Service
public class LikeTsService implements ILikeTsService {

	@Autowired 
	UserRepository userrepository ;
	@Autowired(required=false)
	LikeTsRepository liketsrepository ;
	@Autowired
	FinancialTrainingRepository financialtrainingrepository ; 
	
	@Override
	public void AddDislikeToFinancialTraining(LikeTs likes, Long id_User, Long idTraining) {
		Iterable<User> user = userrepository.findAll();
		for (User user2 : user) {
		if (liketsrepository.DislikesTs(idTraining, id_User)==0) {
			
				if (user2.getId_User()== id_User) {
					financialtrainingrepository.findById(idTraining).map(p -> {
						  likes.setInteractionType(InteractionTs.Dislike);
						  likes.setFt_like(p);
						  likes.setUser(user2);
						  likes.setLiked(false);
						  System.out.println("ok");
							
							return p;
					  });

				
				
			}
			
				liketsrepository.save(likes);
		}
		
		}
		
	}

	@Override
	public void Deletelike(Long id_User, Long idTraining) {
		if (liketsrepository.FindUserByIdFromLikes(id_User, idTraining)==1){
			liketsrepository.DeleteLike(id_User, idTraining);}
		
	}

	@Override
	public void DeleteDislike(Long id_User, Long idTraining) {
        if (liketsrepository.FindUserByIdFromDislikes(id_User, idTraining)==1){
			
			liketsrepository.DeleteDisLike(id_User, idTraining);
			
		}
		
	}

	@Override
	public void AddLikesToFinancialTraining(LikeTs likets, Long id_User, Long idTraining) {
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
