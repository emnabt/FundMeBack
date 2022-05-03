package tn.esprit.fundme.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
  

@Entity
public class Supplier implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private long ID ;
private String description;
private String type;
private String address;
private long phone_number ;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier")
private Set<Product> product;




public long getID() {
	return ID;
}
 
public void setID(long iD) {
	ID = iD;
}
public Set<Product> getProduct() {
	return product;
}
public void setProduct(Set<Product> product) {
	this.product = product;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public long getPhone_number() {
	return phone_number;
}
public void setPhone_number(long phone_number) {
	this.phone_number = phone_number;
}

@Override
public String toString() {
	return "supplier [id_supplier=" + ID + ", description=" + description + ", type=" + type + ", address="
			+ address + ", phone_number=" + phone_number + "]";
}


}
