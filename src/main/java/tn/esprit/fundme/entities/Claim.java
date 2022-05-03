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
@Table (name="Claims")
public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id_Claim")
	private Long id_Claim ;
	private String typeClaims ;
	private String descriptionClaim ; 
	@Temporal(TemporalType.DATE)
	private Date DateClaim ;
	private String EtatClaim ;
	private Long ID_Credit ;
	
	@ManyToOne
	User User_Claim;
	
	@ManyToOne
	Product Product_claim ;
	
	/*@ManyToOne
	Credits Credits_claim ;*/
	
	@ManyToOne
	FinancialTraining financialtraining_claim ;
	
	
	public Long getId_Claim() {
		return id_Claim;
	}

	public void setId_Claim(Long id_Claim) {
		this.id_Claim = id_Claim;
	}

	public Date getDateClaim() {
		return DateClaim;
	}

	public void setDateClaim(Date dateClaim) {
		DateClaim = dateClaim;
	}

	public String getEtatClaim() {
		return EtatClaim;
	}

	public void setEtatClaim(String etatclaim) {
		EtatClaim = etatclaim;
	}


	public String getTypeClaims() {
		return typeClaims;
	}

	public void setTypeClaims(String typeClaims) {
		this.typeClaims = typeClaims;
	}

	public String getDescriptionClaim() {
		return descriptionClaim;
	}

	public void setDescriptionClaim(String descriptionClaim) {
		this.descriptionClaim = descriptionClaim;
	}
	

	public User getUser_Claim() {
		return User_Claim;
	}

	public void setUser_Claim(User user_Claim) {
		User_Claim = user_Claim;
	}
	

	public Product getProduct_claim() {
		return Product_claim;
	}

	public void setProduct_claim(Product product_claim) {
		Product_claim = product_claim;
	}

 
	public FinancialTraining getFinancialtraining_claim() {
		return financialtraining_claim;
	}

	public void setFinancialtraining_claim(FinancialTraining financialtraining_claim) {
		this.financialtraining_claim = financialtraining_claim;
	}
	
	

	public Long getID_Credit() {
		return ID_Credit;
	}

	public void setID_Credit(Long iD_Credit) {
		ID_Credit = iD_Credit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Claim(Long id_Claim, String typeClaims, String descriptionClaim, Date dateClaim, String etatClaim,
			User user_Claim, Product product_claim,  FinancialTraining financialtraining_claim) {
		super();
		this.id_Claim = id_Claim;
		this.typeClaims = typeClaims;
		this.descriptionClaim = descriptionClaim;
		DateClaim = dateClaim;
		EtatClaim = etatClaim;
		User_Claim = user_Claim;
		Product_claim = product_claim;
 		this.financialtraining_claim = financialtraining_claim;
	}

	public Claim() {
		super();
		
	}

	
	

	
	
	

}
