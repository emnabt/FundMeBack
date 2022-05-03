package tn.esprit.fundme.services;

import tn.esprit.fundme.entities.LikeTs;

public interface ILikeTsService {
	
	public void AddDislikeToFinancialTraining(LikeTs likes, Long id_User ,Long idTraining);
	public void Deletelike(Long id_User,Long idTraining);
	public void DeleteDislike(Long id_User,Long idTraining);
	
	public void AddLikesToFinancialTraining(LikeTs likets,Long id_User,Long idTraining);


}
