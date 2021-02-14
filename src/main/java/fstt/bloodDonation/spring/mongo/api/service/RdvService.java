package fstt.bloodDonation.spring.mongo.api.service;

import java.util.List;
import java.util.Optional;


import fstt.bloodDonation.spring.mongo.api.model.Rdv;


public interface RdvService {
	
	
	
	public String saveRdv(Rdv rdv) ;
	
	public List<Rdv> getRdv() ;
	
	public Optional<Rdv> getRdv(int id) ;
		
	public String deleteRdv(int id) ;
	
}
