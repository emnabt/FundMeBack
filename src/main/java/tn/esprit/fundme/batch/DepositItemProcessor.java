package tn.esprit.fundme.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


import tn.esprit.fundme.entities.Deposit;

import java.util.HashMap;
import java.util.Map;

@Component
public class DepositItemProcessor implements ItemProcessor<Deposit, Deposit> {

    
    private static final Map<String, String> environment =
            new HashMap<>();
    
   

	@Override
	public Deposit process(Deposit item) throws Exception {
		return item;
	}
	

}

