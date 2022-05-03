package tn.esprit.fundme.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.esprit.fundme.entities.Deposit;
import tn.esprit.fundme.repositories.DepositRepository;

@Component
public class Dbwriter implements ItemWriter<Deposit> {

	@Autowired
	private DepositRepository depositRepository ;

	@Override
	public void write(List<? extends Deposit> Deposits) throws Exception {
		 System.out.println("Data Saved for  : " +Deposits);
		 depositRepository.saveAll(Deposits);
		
	}

}