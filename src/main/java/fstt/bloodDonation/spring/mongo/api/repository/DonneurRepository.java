package fstt.bloodDonation.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;

public interface DonneurRepository extends MongoRepository<Donneur, Integer>{

}
