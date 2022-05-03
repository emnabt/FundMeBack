package tn.esprit.fundme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Advertisement;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

}
