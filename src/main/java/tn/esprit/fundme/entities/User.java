package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "User")


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_User")
	private Long id_User;
	
	private String Job ;
	private float Salary;
	private String Civil_status;
	private String Cin;
	@Enumerated(EnumType.STRING)
    private  DomainClient domain ;
	@OneToOne
	private ClientDocuments UserDocuments;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private UserType logUser;
	
	
	public UserType getLogUser() {
		return logUser;
	}
	public void setLogUser(UserType logUser) {
		this.logUser = logUser;
	}
	public ClientDocuments getUserDocuments() {
		return UserDocuments;
	}
	public void setUserDocuments(ClientDocuments userDocuments) {
		UserDocuments = userDocuments;
	}
	public Set<Deposit> getDeposits() {
		return Deposits;
	}
	public void setDeposits(Set<Deposit> deposits) {
		Deposits = deposits;
	}
	 
	public DomainClient getDomainclient() {
		return domain ;
	}
	public void setDomainclient(DomainClient domainclient) {
		this.domain = domainclient;
	}
	public FinancialTraining getFormations_user() {
		return formations_user;
	}
	public void setFormations_user(FinancialTraining formations_user) {
		this.formations_user = formations_user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Claim> getClaims_user() {
		return Claims_user;
	}
	public void setClaims_user(Set<Claim> claims_user) {
		Claims_user = claims_user;
	}
 
	public Set<Product> getProducts_user() {
		return products_user;
	}
	public void setProducts_user(Set<Product> products_user) {
		this.products_user = products_user;
	}
	public Set<Notification> getNotifications_user() {
		return notifications_user;
	}
	public void setNotifications_user(Set<Notification> notifications_user) {
		this.notifications_user = notifications_user;
	}
	@OneToMany(mappedBy="Users_Deposit")
	private Set<Deposit> Deposits;
	
	
	@ManyToOne
	FinancialTraining formations_user;
	
	
	@OneToMany(mappedBy="User_Claim")
	private Set<Claim> Claims_user;
	
	
	 
	
	@OneToMany(mappedBy="user_product")
	private Set<Product> products_user;
	
	
	@OneToMany(mappedBy="user_notification")
	private Set<Notification> notifications_user;
	
	public Long getId_User() {
		return id_User;
	}
	public void setId_User(Long id_User) {
		this.id_User = id_User;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	public float getSalary() {
		return Salary;
	}
	public void setSalary(float salary) {
		Salary = salary;
	}
	public String getCivil_status() {
		return Civil_status;
	}
	public void setCivil_status(String civil_status) {
		Civil_status = civil_status;
	}
	
	
	public String getCin() {
		return Cin;
	}
	public void setCin(String cin) {
		Cin = cin;
	}
	



	
}
