package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table (name="Training")
public class FinancialTraining  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idTraining")
	private Long idTraining ;
	private String Trainer ; 
	private String ImageTrainer ; 
	private String descriptionTrain ;
	private String TypeTrain ;
	@Temporal(TemporalType.DATE)
	private Date dateTrain ;

	
	@OneToMany
	private Set<User> users;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Claim> Claim ; 
	
	@OneToMany(mappedBy = "ft_like", cascade = CascadeType.ALL)
	private List<LikeTs> likets;
	

	public Long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}

	public String getTrainer() {
		return Trainer;
	}

	public void setTrainer(String trainer) {
		Trainer = trainer;
	}

	public String getDescriptionTrain() {
		return descriptionTrain;
	}

	public void setDescriptionTrain(String descriptionTrain) {
		this.descriptionTrain = descriptionTrain;
	}

	public String getTypeTrain() {
		return TypeTrain;
	}

	public void setTypeTrain(String typeTrain) {
		TypeTrain = typeTrain;
	}

	public Date getDateTrain() {
		return dateTrain;
	}

	public void setDateTrain(Date dateTrain) {
		this.dateTrain = dateTrain;
	}


	public String getImageTrainer() {
		return ImageTrainer;
	}

	public void setImageTrainer(String imageTrainer) {
		ImageTrainer = imageTrainer;
	}
	
	
	public Set<Claim> getClaim() {
		return Claim;
	}

	public void setClaim(Set<Claim> claim) {
		Claim = claim;
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	 
	
	public List<LikeTs> getLikets() {
		return likets;
	}

	public void setLikets(List<LikeTs> likets) {
		this.likets = likets;
	}

	
	
	

	public FinancialTraining() {
		super();
	}

	public FinancialTraining(Long idTraining, String trainer, String imageTrainer, String descriptionTrain,
			String typeTrain, Date dateTrain, Set<User> users, Set<tn.esprit.fundme.entities.Claim> claim,
			List<LikeTs> likets) {
		super();
		this.idTraining = idTraining;
		Trainer = trainer;
		ImageTrainer = imageTrainer;
		this.descriptionTrain = descriptionTrain;
		TypeTrain = typeTrain;
		this.dateTrain = dateTrain;
		this.users = users;
		Claim = claim;
		this.likets = likets;
	}

	

	
	
	

}
