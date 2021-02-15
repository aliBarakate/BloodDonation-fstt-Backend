package fstt.bloodDonation.spring.mongo.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;


public interface MedecinRepository extends MongoRepository<Medecin, Integer>{

	Optional<Medecin> findByLogin (String login);
	
}
