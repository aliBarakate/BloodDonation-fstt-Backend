package fstt.bloodDonation.spring.mongo.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fstt.bloodDonation.spring.mongo.api.model.GroupeSanguinChart;
import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;

@Service
public class ReceveurServiceImpl implements ReceveurService{


	@Autowired
	private ReceveurRepository repository;
	
	@Autowired
	private DonneurRepository donneurRepository;
	
	@Autowired
	private SequenceGeneratorService service;
	

	@Override
	public String saveReceveur(Receveur receveur) {
		receveur.setId(service.getSequenceNumber(Receveur.SEQUENCE_NAME));
		repository.save(receveur);
		return "Receveur ajoute avec id : " + receveur.getId();
	}

	@Override
	public List<Receveur> getReceveur() {
		return repository.findAll();
	}

	@Override
	public Optional<Receveur> getReceveur(int id) {
		return repository.findById(id);
	}


	@Override
	public String deleteReceveur(int id) {
		repository.deleteById(id);
		return "Receveur supprime avec id : " + id;
	}

	@Override
	public GroupeSanguinChart Statistique() {
		// TODO Auto-generated method stub
		
		
		String ListGroupesSanguin[]= {"AB_pos","A_pos","B_pos","O_pos","AB_neg","A_neg","B_neg","O_neg"};
		 
		int StatistiqueOffres[]=new int[8];
		int StatistiqueDemandes[]=new int[8];
		
		for(int i=0;i<8;i++)
		{

			StatistiqueOffres[i]=donneurRepository.findByGroupeSanguin(ListGroupesSanguin[i]).size();
			StatistiqueDemandes[i]=repository.findByGroupeSanguin(ListGroupesSanguin[i]).size();
			
		}
		
		return new GroupeSanguinChart(StatistiqueDemandes[0],StatistiqueDemandes[1],StatistiqueDemandes[2],
				StatistiqueDemandes[3],StatistiqueDemandes[4],StatistiqueDemandes[5],
				StatistiqueDemandes[6],StatistiqueDemandes[7],StatistiqueOffres[0],StatistiqueOffres[1],StatistiqueOffres[2]
				,StatistiqueOffres[3],StatistiqueOffres[4],StatistiqueOffres[5],StatistiqueOffres[6]
						,StatistiqueOffres[7]);
	}
	

	
	
}
