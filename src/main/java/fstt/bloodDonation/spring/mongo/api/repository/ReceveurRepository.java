package fstt.bloodDonation.spring.mongo.api.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import fstt.bloodDonation.spring.mongo.api.model.Receveur;

public interface ReceveurRepository extends MongoRepository<Receveur, Integer>{

	public List<Receveur> findByGroupeSanguin(String grpSanguin);
	
}
