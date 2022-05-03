package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Agent")


public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAgent")
	private Long idAgent;
	private String Cin;	
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private UserType logAgent;
	
	public UserType getLogAgent() {
		return logAgent;
	}

	public void setLogAgent(UserType logAgent) {
		this.logAgent = logAgent;
	}

	@ManyToOne
	Admin admins;
	
	@OneToMany(mappedBy="agents")
	private Set<Advertisement> ListAdverisements;
	
	
 
	public Long getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Long idAgent) {
		this.idAgent = idAgent;
	}	

	public String getCin() {
		return Cin;
	}

	public void setCin(String cin) {
		Cin = cin;
	}


	public Admin getAdmins() {
		return admins;
	}

	public void setAdmins(Admin admins) {
		this.admins = admins;
	}

	public Set<Advertisement> getListAdverisements() {
		return ListAdverisements;
	}

	public void setListAdverisements(Set<Advertisement> listAdverisements) {
		ListAdverisements = listAdverisements;
	}

 
	
	
	

}
