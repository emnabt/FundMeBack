package tn.esprit.fundme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import tn.esprit.fundme.entities.Claim;
import tn.esprit.fundme.entities.DictionnaireDesMotsInterdits;
import tn.esprit.fundme.entities.FinancialTraining;
import tn.esprit.fundme.entities.Product;
import tn.esprit.fundme.entities.User;
 import tn.esprit.fundme.repositories.FinancialTrainingRepository;
import tn.esprit.fundme.repositories.ProductsRepository;
import tn.esprit.fundme.repositories.UserRepository;


@Service
public class ClaimServiceImpl implements IClaimService {

	@Autowired
	tn.esprit.fundme.repositories.ClaimRepository claimrepository ;
	@Autowired
	FinancialTrainingRepository  financialtrainingrepository  ;
	@Autowired
	ProductsRepository productrepository ;
	@Autowired
	UserRepository userrepository ;
	@Autowired
	EmailSenderService emailsenderservice ; 
	
	
	// public static String[] BadWords = {"shit , fuck , fghbj "} ;
	// public static Set<String> myset = new HashSet<String>(Arrays.asList(BadWords));
	
	
	@Override
	public List<Claim> retrieveAllClaims() {
		return (List<Claim>) claimrepository.findAll() ;
	}

	@Override
	public Claim addClaim(Claim c , Long id_User) {
		User user=userrepository.findById(id_User).get();
		c.setUser_Claim(user);
		try {
			emailsenderservice.sendSimpleEmail("emna.bentaher@esprit.tn", "claim sent", "claim"); ;
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
	    System.out.println( "Congratulations! Your mail has been send to the user.");
	    
		claimrepository.save(c);
		return c ;
	}

	@Override
	public void deleteClaim(Long id_Claim) {
		claimrepository.deleteById(id_Claim);
		
	}

	@Override
	public Claim updateClaim(Claim c, Long id_Claim ) {
		Claim claimModif = claimrepository.findById(id_Claim).get(); 
		claimModif.setDateClaim(c.getDateClaim());
		claimModif.setDescriptionClaim(c.getDescriptionClaim());
		claimModif.setEtatClaim(c.getEtatClaim());
		claimModif.setTypeClaims(c.getTypeClaims());
		claimrepository.save(claimModif) ;
		return c ;
	}

	@Override
	public Claim retrieveClaim(Long id_Claim) {
		return claimrepository.findById(id_Claim).orElse(null) ;
	}
	

	@Override
	public void assignClaimToProduct(Long product_id, Long id_Claim) {
	Product p = productrepository.findById(product_id).orElse(null);
	Claim c = claimrepository.findById(id_Claim).orElse(null);
	c.setProduct_claim(p);
	claimrepository.save(c);
	}
	
	/*@Override
	public void assignClaimToCredit(Long ID, Long id_Claim) {
	 
	Claim c = claimrepository.findById(id_Claim).orElse(null);
	c.setID_Credit(ID);
	claimrepository.save(c);
	}*/
	
	@Override
	public void assignClaimToFinancialTraining(Long idTraining, Long id_Claim) {
	FinancialTraining ft = financialtrainingrepository.findById(idTraining).orElse(null);
	Claim c = claimrepository.findById(id_Claim).orElse(null);
	c.setFinancialtraining_claim(ft);
	claimrepository.save(c);
	}
	
	
	
	@Override
	public List<Claim> retrieveAllClaimsByProduct(Long product_id) {
		List<Claim> listclai = claimrepository.getClaimsByProduit(product_id);
		for (Claim c : listclai) {
			System.out.println("Client :" + c);
		}

		return (List<Claim>) claimrepository.getClaimsByProduit(product_id);
	}
	
	/*
	@Override
	public List<Claim> retrieveAllClaimsByCredit(Long ID) {
		List<Claim> listclai = claimrepository.getClaimsByCredit(ID);
		for (Claim c : listclai) {
			System.out.println("Client :" + c);
		}

		return (List<Claim>) claimrepository.getClaimsByCredit(ID);
		
	}*/
	
	@Override
	public List<Claim> retrieveAllClaimsByFinancialTraining(Long idTraining) {
		List<Claim> listclai = claimrepository.getClaimsByFinancialTraining(idTraining);
		for (Claim c : listclai) {
			System.out.println("Client :" + c);
		}

		
		return (List<Claim>) claimrepository.getClaimsByFinancialTraining(idTraining);
		
	}
	
	/*
	@Override
	public  boolean checkWord(String description) {
		return myset.contains(description);
		//String BadWordsArray[] = BadWords.split(",") ;
		//for (int i=0 ; i<BadWordsArray.length ; i++) {
			//if (description.indexOf(BadWordsArray[i])>-1 )
				//return true ;
		//}
		//return false ; 

		}*/

	
	@Override
	public String checkmots(Claim c, Long id) {
		List<DictionnaireDesMotsInterdits> dic = claimrepository.Dictionnaire();
		for (int i = 1; i <= dic.size(); i++) {
			if (c.getDescriptionClaim().contains(dic.get(i-1).getMots())) {
				break;
			}
			else{
				if (i == dic.size()) {
					return "claim added succesfully";
				}
			}
			
		}
		return "can not add claim which contains a forbidden word";
		
		
	}
	   
		

	}
	
	
