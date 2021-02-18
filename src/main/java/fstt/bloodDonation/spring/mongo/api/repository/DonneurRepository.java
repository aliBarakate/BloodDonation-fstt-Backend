package fstt.bloodDonation.spring.mongo.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;

public interface DonneurRepository extends MongoRepository<Donneur, Integer>{
	
	
	public List<Donneur> findByVilleAndGroupeSanguinOrderByCompteur(String ville,String GrpSanguin);

	public Donneur findByCin(String cin);
	
	public List<Donneur> findByActif(int actif);
	
	public List<Donneur> findByVilleAndGroupeSanguinAndActifOrderByCompteur(String ville,String GrpSanguin,int Actif);

    public List<Donneur> findByGroupeSanguin(String grpSanguin);

	
}
