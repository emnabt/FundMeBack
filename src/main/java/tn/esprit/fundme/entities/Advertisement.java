package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Advertisement")


public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAdvertisement")
	
	private Long idAdvertisement;
	private String SocietyName ;
	private float Balance;
	private String Description;
	private String Image;
	private String Etat;
	@Temporal(TemporalType.DATE)
	private Date DateStart;
	@Temporal(TemporalType.DATE)
	private Date DateFin;
	
	@ManyToOne
	Agent agents;

	public Long getIdAdvertisement() {
		return idAdvertisement;
	}

	public void setIdAdvertisement(Long idAdvertisement) {
		this.idAdvertisement = idAdvertisement;
	}

	public String getSocietyName() {
		return SocietyName;
	}

	public void setSocietyName(String societyName) {
		SocietyName = societyName;
	}

	public float getBalance() {
		return Balance;
	}

	public void setBalance(float balance) {
		Balance = balance;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getEtat() {
		return Etat;
	}

	public void setEtat(String etat) {
		Etat = etat;
	}

	public Date getDateStart() {
		return DateStart;
	}

	public void setDateStart(Date dateStart) {
		DateStart = dateStart;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	public Agent getAgents() {
		return agents;
	}

	public void setAgents(Agent agents) {
		this.agents = agents;
	}

	public Advertisement(Long idAdvertisement, String societyName, float balance, String description, String image,
			String etat, Date dateStart, Date dateFin, Agent agents) {
		super();
		this.idAdvertisement = idAdvertisement;
		SocietyName = societyName;
		Balance = balance;
		Description = description;
		Image = image;
		Etat = etat;
		DateStart = dateStart;
		DateFin = dateFin;
		this.agents = agents;
	}

	public Advertisement() {
		
	}
	
	
}
