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

import fstt.bloodDonation.spring.mongo.api.model.Medecin;
import fstt.bloodDonation.spring.mongo.api.repository.MedecinRepository;

@Service
public class MedecinServiceImpl implements MedecinService{
	
	@Autowired
	private MedecinRepository repository;
	
	@Autowired
	private SequenceGeneratorService service;
	

	@Override
	public String saveMedecin(Medecin medecin) {
		medecin.setId(service.getSequenceNumber(Medecin.SEQUENCE_NAME));
		repository.save(medecin);
		return "Medecin ajoute avec id : " + medecin.getId();
	}

	

	@Override
	public List<Medecin> getMedecin() {
		return repository.findAll();
	}

	@Override
	public Optional<Medecin> getMedecin(int id) {
		return repository.findById(id);
	}

	@Override
	public String deleteMedecin(int id) {
		repository.deleteById(id);
		return "Medecin supprime avec id : " + id;
	}


	
}
