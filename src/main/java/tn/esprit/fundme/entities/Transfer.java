package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table( name = "Transfer") //optionnel
public class Transfer  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdTransfer") //optionnel 
	private Long idtransfer ; //clé primaire
	private Long ribdest ;
	private Float amount ;
	@Temporal(TemporalType.DATE) 
	private Date dateTransfer ;
	
	
	/*@ManyToMany(mappedBy="transfers")
	private Set<Deposit> Deposits;
	*/

	@ManyToOne
	Deposit deposit;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="transf")
	private Set<Deposit> Deposits;*/
	
	
	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Transfer(Long idtransfer, Long ribdest, Float amount, Date dateTransfer, Deposit deposit) {
		super();
		this.idtransfer = idtransfer;
		this.ribdest = ribdest;
		this.amount = amount;
		this.dateTransfer = dateTransfer;
		this.deposit = deposit;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	public Transfer(Long idtransfer, Long ribdest, String amount, Date dateTransfer, Deposit deposit,
			Set<Deposit> deposits) {
		super();
		this.idtransfer = idtransfer;
		this.ribdest = ribdest;
		
		this.dateTransfer = dateTransfer;
		this.deposit = deposit;
		
	}



	public Transfer(Long idtransfer, Long ribdest, String amount, Date dateTransfer, Deposit deposit) {
		super();
		this.idtransfer = idtransfer;
		this.ribdest = ribdest;
		
		this.dateTransfer = dateTransfer;
		this.deposit = deposit;
	}


	public Deposit getDeposit() {
		return deposit;
	}
	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getIdtransfer() {
		return idtransfer;
	}
	public void setIdtransfer(Long idtransfer) {
		this.idtransfer = idtransfer;
	}
	public Long getRibdest() {
		return ribdest;
	}
	public void setRibdest(Long ribdest) {
		this.ribdest = ribdest;
	}
	
	public Date getDateTransfer() {
		return dateTransfer;
	}
	public void setDateTransfer(Date dateTransfer) {
		this.dateTransfer = dateTransfer;
	}
	public Transfer(Long idtransfer, Long ribdest, String amount, Date dateTransfer) {
		super();
		this.idtransfer = idtransfer;
		this.ribdest = ribdest;
		
		this.dateTransfer = dateTransfer;
	}
	
	
	
}

