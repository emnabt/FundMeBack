package tn.esprit.fundme.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "ClientDocuments")


	public class ClientDocuments implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID_card")
		private long ID_card;	
		private File documents;
		
	public File getDocuments() {
			return documents;
		}
		public void setDocuments(File documents) {
			this.documents = documents;
		}
	private long Phone;
	
	public long getID_card() {
		return ID_card;
	}
	public void setID_card(long iD_card) {
		ID_card = iD_card;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(long phone) {
		Phone = phone;
	}


	
	}
