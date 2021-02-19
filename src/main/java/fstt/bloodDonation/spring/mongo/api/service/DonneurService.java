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
	
	public void SendMail(String mail);
	
	public Donneur getByCin(String cin);
	
	public String deleteByCin(String cin);
	
	public String updateDonneurByCin(String cin,String grpSanguin);
	
	public String updateDonneur(Donneur d);
	
	public String updateInactifDonneur();
	
	public List<Donneur> getDonneursForReceveur(); 
	
	public void demandeAccepte(String mail); 
	
	public void ActiverDonneur(int id);
	
}
