package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Date;

 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")


public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id ;
	
	
	private String type;
	private String description;
	private float value;
	private float interetrate ;
	private Date  start_date ; 
	private int quantity;
	
	@ManyToOne	 
	private Supplier supplier ;
	
	@ManyToOne
	User user_product;
	
	
	
 
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getInteretrate() {
		return interetrate;
	}
	public void setInteretrate(float interetrate) {
		this.interetrate = interetrate;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public User getUser_product() {
		return user_product;
	}
	public void setUser_product(User user_product) {
		this.user_product = user_product;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", type=" + type + ", description=" + description + ", value="
				+ value + ", interetrate=" + interetrate + ", start_date=" + start_date + ", supplier=" + supplier
				+ ", user_product=" + user_product + "]";
	}
	
	
}
