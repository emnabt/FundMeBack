package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "Deposit") //optionnel
public class Deposit  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Rib") //optionnel 
	private Long rib ; //clé primaire
	
	
	private Float balance ;
	@Temporal(TemporalType.DATE) 
	private Date dateCreationdeposit ;
	
	
	@Override
	public String toString() {
		return "Deposit [rib=" + rib + ", balance=" + balance + ", dateCreationdeposit=" + dateCreationdeposit
				+ ", Users_Deposit=" + Users_Deposit + ", trans=" + trans + "]";
	}
	@ManyToOne
	User Users_Deposit;
	
	
	/*@ManyToMany()
	private Set<Transfer> transfers;*/
	
	
	@OneToMany(mappedBy="deposit")
	private Set<Transfer> trans;
	
	/*@ManyToOne
	Transfer transf;*/
	
	
	
	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Deposit(Long rib, String balance, Date dateCreationdeposit, User users_Deposit, Set<Transfer> transfers,
			Set<Transfer> trans) {
		super();
		this.rib = rib;
		
		this.dateCreationdeposit = dateCreationdeposit;
		Users_Deposit = users_Deposit;
		
		this.trans = trans;
	}



	public Deposit(Long rib, Float balance, Date dateCreationdeposit, User users_Deposit, Set<Transfer> trans) {
		super();
		this.rib = rib;
		this.balance = balance;
		this.dateCreationdeposit = dateCreationdeposit;
		Users_Deposit = users_Deposit;
		this.trans = trans;
	}



	public Float getBalance() {
		return balance;
	}



	public void setBalance(Float balance) {
		this.balance = balance;
	}



	public User getUsers_Deposit() {
		return Users_Deposit;
	}



	public void setUsers_Deposit(User users_Deposit) {
		Users_Deposit = users_Deposit;
	}



	



	public Deposit(Long rib, String balance, Date dateCreationdeposit, User users_Deposit, Transfer transfers,
			Set<Transfer> trans) {
		super();
		this.rib = rib;
		
		this.dateCreationdeposit = dateCreationdeposit;
		Users_Deposit = users_Deposit;
		
		this.trans = trans;
	}






	public Set<Transfer> getTrans() {
		return trans;
	}



	public void setTrans(Set<Transfer> trans) {
		this.trans = trans;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Deposit(Long rib, String balance, Date dateCreationdeposit) {
		super();
		this.rib = rib;
		
		this.dateCreationdeposit = dateCreationdeposit;
	}
	
	
	
	public Long getRib() {
		return rib;
	}
	public void setRib(Long rib) {
		this.rib = rib;
	}
	
	public Date getDateCreationdeposit() {
		return dateCreationdeposit;
	}
	public void setDateCreationdeposit(Date dateCreationdeposit) {
		this.dateCreationdeposit = dateCreationdeposit;
	} 
	
}
