package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Product;

public interface ProductsService {
	List<Product> retrieveAllProduct(); 
	Product addProduct(Product P); 
	Product updateProduct(Product P);
	Product retrieveProduct(Long id); 
	void removeProduct(Long id);
	void checkProducts();
}
