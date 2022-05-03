package tn.esprit.fundme.batch;

import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
 
import tn.esprit.fundme.entities.Product;
import tn.esprit.fundme.entities.Supplier;
 
public class ProductFieldSetMapper implements FieldSetMapper<Product> {
		List<Supplier> s ;
    
    public ProductFieldSetMapper(List<Supplier> s) {
		super();
		this.s = s;
	}

	@Override
    public Product mapFieldSet(FieldSet fieldSet) {
 
        Product p = new Product();
        p.setType(fieldSet.readString(0));
        p.setDescription(fieldSet.readString(1));
        p.setValue(fieldSet.readFloat(2));
        p.setQuantity(fieldSet.readInt(4));
        p.setInteretrate(fieldSet.readFloat(3));
     for(Supplier x : s) {
     		if(x.getID()==fieldSet.readLong(5))
     		{
     		 
     			p.setSupplier(x);
     		}
     		else
     			p.setSupplier(null);
     	            }
 
    	return p;
    }
}
