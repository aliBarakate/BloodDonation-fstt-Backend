package fstt.bloodDonation.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;

public interface MedecinRepository extends MongoRepository<Medecin, Integer>{

}
