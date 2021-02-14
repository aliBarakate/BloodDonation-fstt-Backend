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

import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;

@Service
public class ReceveurServiceImpl implements ReceveurService{


	@Autowired
	private ReceveurRepository repository;
	
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
	

	
	
}
