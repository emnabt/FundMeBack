package tn.esprit.fundme.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.Advertisement;
import tn.esprit.fundme.entities.Agent;
import tn.esprit.fundme.repositories.AdvertisementRepository;
import tn.esprit.fundme.repositories.AgentRepository;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService {
	
	@Autowired(required=false)
	AgentRepository agrepo;

	@Autowired(required=false)
	AdvertisementRepository advertisementrepository; 
	
	
	@Override
	public List<Advertisement> retrieveAllAdvertisements() {
		return 	 (List<Advertisement>) advertisementrepository.findAll();

	}

	@Override
	public Advertisement addAdvertisement(Advertisement s) {
Agent a = agrepo.findById(s.getAgents().getIdAgent()).orElse(null);
Set<Advertisement> lsa=a.getListAdverisements();

		if(a!=null) {
			lsa.add(s);
         a.setListAdverisements(lsa);
		  advertisementrepository.save(s);		
		}
			return s;
	
	}
	@Override
	public Advertisement updateAdvertisement(Advertisement u) {
Agent a = agrepo.findById(u.getAgents().getIdAgent()).orElse(null);
		
		if(a!=null)
		advertisementrepository.save(u);
		
		return u;
	}

	@Override
	public Advertisement retrieveAdvertisement(Long id) {
		return  advertisementrepository.findById(id).orElse(null);

	}

	@Override
	public void removeAdvertisement(Long id) {
		advertisementrepository.deleteById(id);		
	}

}
