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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Administrateur")


public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAdmin")
	private Long idAdmin;


@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
private UserType logAdmin;


	public UserType getLogAdmin() {
		return logAdmin;
	}

	public void setLogAdmin(UserType log) {
		this.logAdmin = log;
	}


	
	@OneToMany(mappedBy="admins")
	private Set<Agent> ListAgents;

    public Admin(Long idAdmin, String mdp, Set<Agent> listAgents) {
		super();
		this.idAdmin = idAdmin;
		ListAgents = listAgents;
	}

    public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public Set<Agent> getListAgents() {
		return ListAgents;
	}


	public void setListAgents(Set<Agent> listAgents) {
		ListAgents = listAgents;
	}

	public Long getIdAdmin() {
		return idAdmin;
	}


	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
	
}