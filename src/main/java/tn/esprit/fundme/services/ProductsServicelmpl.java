package tn.esprit.fundme.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.Product;
import tn.esprit.fundme.repositories.ProductsRepository;

@Service
public class ProductsServicelmpl implements ProductsService {
	@Autowired
	ProductsRepository ProductsRepository;
	@Autowired 
	EmailSenderService email;
	
	@Override
	public List<Product> retrieveAllProduct()
	{
		return (List<Product>) ProductsRepository.findAll();
	}
	@Override
	public Product addProduct(Product P)
	{   
		Product x = ProductsRepository.findByType(P.getType()).orElse(null);
		if(x!=null) {
			x.setQuantity(x.getQuantity()+P.getQuantity());
			}
		else x=ProductsRepository.save(P);
		return x;

	}
	@Override
	public Product updateProduct(Product P)
	{
		ProductsRepository.save(P);
		return P;
	}
	@Override
	public Product retrieveProduct(Long id) {
		return ProductsRepository.findById(id).orElse(null);
	}
	@Override
	public void removeProduct(Long id) {
		ProductsRepository.deleteById(id);

	}
	
	@Scheduled(cron = "0 0 8 * * *")
	@Override
	public void checkProducts() {
		List<Product> pp = ProductsRepository.checkQuantity();
		System.out.println(pp);
		for (Iterator iterator = pp.iterator(); iterator.hasNext();) {
			System.out.println("heeeeeeeeeeeeeeyyyy");

			Product product = (Product) iterator.next();
			email.sendSimpleEmail("mohammedamine.kridiss@esprit.tn","Notification de rupture de stock","rupture de stock");

			
		}
		
	}
}
