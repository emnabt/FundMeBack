package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Supplier;

public interface SupplierService {
	List<Supplier> retrieveAllSupplier(); 
	Supplier addSupplier(Supplier S); 
	Supplier updateSupplier(Supplier S);
	Supplier retrieveSupplier(Long id); 
	void removeSupplier(Long id);
}
