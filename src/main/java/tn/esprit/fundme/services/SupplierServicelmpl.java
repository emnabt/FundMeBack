package tn.esprit.fundme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.Supplier;
import tn.esprit.fundme.repositories.SupplierRepository;
@Service
public class SupplierServicelmpl implements SupplierService{
	@Autowired
	SupplierRepository SupplierRepository;
	
	@Override
	public List<Supplier> retrieveAllSupplier()
	{
		return (List<Supplier>) SupplierRepository.findAll();
	}
	@Override
	public Supplier addSupplier(Supplier S)
	{
		return SupplierRepository.save(S);

	}
	@Override
	public Supplier updateSupplier(Supplier S) {
		
		SupplierRepository.save(S);
		return S;
	}
	@Override
	public Supplier retrieveSupplier(Long id) 
	{
		return SupplierRepository.findById(id).orElse(null);

	}
	@Override
	public void removeSupplier(Long id)
	{
		SupplierRepository.deleteById(id);

	}

}
