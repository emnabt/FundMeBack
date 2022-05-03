package tn.esprit.fundme.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.LikeTs;


@Repository
public interface LikeTsRepository extends CrudRepository<LikeTs , Long> {
	
	@Query(value="select count(*) from Likets l where l.ft_like.idTraining= :idT and l.user.id_User = :id and interactionType = 'like' and isLiked=1",nativeQuery=true)
	int LikesTs(@Param("idT") Long idts, @Param("id") Long id_User);


	@Query(value="select count(*) from Likets l where l.ft_like.idTraining= :idT and l.user.id_User = :iduser and interactionType = 'Dislike' and isLiked=0",nativeQuery=true)
	int DislikesTs(@Param("idT") Long idTraining, @Param("iduser") Long id_User);

	
	@Transactional
	@Modifying
	@Query(value="delete l from Likets l where l.user.id_User=:id and l.ft_like.idTraining = :idT and l.isLiked=1 ",nativeQuery=true)
	void DeleteLike(@Param("id") Long id_User, @Param("idT") Long idTraining);

	@Query(value="select count(*) from Likets l where l.user.id_User= :id_User and l.ft_like.idTraining= :idT ",nativeQuery=true)
	int FindUserByIdFromLikes(@Param("id_User") Long id_User, @Param("idT") Long idTraining);

	@Transactional
	@Modifying
	@Query(value="delete l from Likets l where l.user.id_User= :iduser and l.ft_like.idTraining = :idT and l.isLiked=0 ",nativeQuery=true)
	void DeleteDisLike(@Param("iduser") Long id_User, @Param("idT") Long idTraining);
	
	@Query(value="select count(*) from Likets l where l.user.id_User= :iduser and l.ft_like.idTraining= :idT and l.isLiked = 0",nativeQuery=true)
	int FindUserByIdFromDislikes(@Param("iduser") Long id_User, @Param("idT") Long idTraining);


}
