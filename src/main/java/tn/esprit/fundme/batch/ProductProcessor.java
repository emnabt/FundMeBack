package tn.esprit.fundme.batch;

 
import org.springframework.batch.item.ItemProcessor;

import tn.esprit.fundme.entities.Product;

 
public class ProductProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product product) {
    	System.out.println("processooooooooooooor");
        product.setType(product.getType().toUpperCase());
        return product;
    }
}