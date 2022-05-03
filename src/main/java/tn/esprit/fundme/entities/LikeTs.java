package tn.esprit.fundme.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Likets")
public class LikeTs {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Like_id;
	
	private boolean isLiked;
	@Enumerated(EnumType.STRING)
	private InteractionTs interactionType;
	
	
	@ManyToOne
	private FinancialTraining ft_like ;
	
	@ManyToOne
	private User user;

	public Long getLike_id() {
		return Like_id;
	}

	public void setLike_id(Long like_id) {
		Like_id = like_id;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public InteractionTs getInteractionType() {
		return interactionType;
	}

	public void setInteractionType(InteractionTs interactionType) {
		this.interactionType = interactionType;
	}

	public FinancialTraining getFt_like() {
		return ft_like;
	}

	public void setFt_like(FinancialTraining ft_like) {
		this.ft_like = ft_like;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LikeTs(Long like_id, boolean isLiked, InteractionTs interactionType, FinancialTraining ft_like, User user) {
		super();
		Like_id = like_id;
		this.isLiked = isLiked;
		this.interactionType = interactionType;
		this.ft_like = ft_like;
		this.user = user;
	}

	public LikeTs() {
		super();
		
	}
	
	
}
