package fstt.bloodDonation.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fstt.bloodDonation.spring.mongo.api.model.Receveur;

public interface ReceveurRepository extends MongoRepository<Receveur, Integer>{

}
