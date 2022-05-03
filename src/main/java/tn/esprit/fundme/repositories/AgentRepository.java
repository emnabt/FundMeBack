package tn.esprit.fundme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.fundme.entities.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
	@Query("SELECT f FROM Agent f WHERE f.admins.idAdmin= :fourniss")
	List<Agent> getAgentsbyAdmin(@Param("fourniss") Long idFournisseur);
}
