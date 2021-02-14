package fstt.bloodDonation.spring.mongo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;

public interface DonneurService {
	
	public String saveDonneur(Donneur donneur); 
	
	public List<Donneur> getDonneurs() ;
	
	public List<Donneur> getDonneursForReceveur(String ville,String groupeSanguin); 
	
	public Optional<Donneur> getDonneur(int id); 

	public String deleteDonneur( int id); 
}
