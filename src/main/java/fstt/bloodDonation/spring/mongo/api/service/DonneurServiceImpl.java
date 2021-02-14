package fstt.bloodDonation.spring.mongo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;

@Service
public class DonneurServiceImpl implements DonneurService {
	
	@Autowired
	private DonneurRepository repository;
	@Autowired
	private SequenceGeneratorService service;
	

	@Override
	public String saveDonneur(Donneur donneur) {
		donneur.setId(service.getSequenceNumber(Donneur.SEQUENCE_NAME));
		repository.save(donneur);
		return "Donneur ajote avec id : " + donneur.getId();
	}

	@Override
	public List<Donneur> getDonneurs() {
		return repository.findAll();
	}
	
	@Override
	public List<Donneur> getDonneursForReceveur(String ville,String groupeSanguin) {
		List<Donneur> listDonneur=new ArrayList<Donneur>();
		listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville, groupeSanguin);
		
		if(listDonneur.isEmpty())
		{
			switch (groupeSanguin) {
			
			case "O_neg":
				return listDonneur;
			
			case "O_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "A_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "A_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");
				
				if(listDonneur.isEmpty())	{		
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				}
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
				
			case "B_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "B_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
				
			case "AB_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
						
			case "AB_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"AB_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_pos");

				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");				
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_pos");
						
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;			

			}		
			
		}
	
		return listDonneur;
		
	}
	

	@Override
	public Optional<Donneur> getDonneur(int id) {
		return repository.findById(id);
	}

	@Override
	public String deleteDonneur( int id) {
		repository.deleteById(id);
		return "Donneur supprime avec id : " + id;
	}

}
