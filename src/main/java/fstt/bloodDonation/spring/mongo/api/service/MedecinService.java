package fstt.bloodDonation.spring.mongo.api.service;

import java.util.List;
import java.util.Optional;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;

public interface MedecinService {
	

	public String saveMedecin(Medecin medecin) ;
	public List<Medecin> getMedecin();
	public Optional<Medecin> getMedecin(int id);
	public String deleteMedecin(int id); 
}
