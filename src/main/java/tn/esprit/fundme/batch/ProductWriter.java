package tn.esprit.fundme.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.fundme.entities.Product;
 import tn.esprit.fundme.services.ProductsServicelmpl;
 
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductsServicelmpl productService;

    
	@Override
	public void write(List<? extends Product> items) throws Exception {
		 items.stream().forEach(p -> {
			 
 			 productService.addProduct(p);
        });
		
	}
}