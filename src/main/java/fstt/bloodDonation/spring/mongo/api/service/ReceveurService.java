package fstt.bloodDonation.spring.mongo.api.service;

import java.util.List;
import java.util.Optional;

import fstt.bloodDonation.spring.mongo.api.model.Receveur;

public interface ReceveurService {
	
	
	public String saveReceveur(Receveur receveur);
	public List<Receveur> getReceveur(); 
	
	public Optional<Receveur> getReceveur(int id) ;
	public String deleteReceveur(int id); 
}
