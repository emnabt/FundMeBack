package tn.esprit.fundme.services;

import java.util.List;

import tn.esprit.fundme.entities.Advertisement;

public interface IAdvertisementService {

	List<Advertisement> retrieveAllAdvertisements();

	Advertisement addAdvertisement (Advertisement a);

	Advertisement updateAdvertisement (Advertisement a);

	Advertisement retrieveAdvertisement (Long id);

	void removeAdvertisement (Long id);
}
