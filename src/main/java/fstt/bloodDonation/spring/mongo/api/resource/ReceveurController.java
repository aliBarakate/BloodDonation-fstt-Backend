package fstt.bloodDonation.spring.mongo.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;

@RestController
public class ReceveurController {

	@Autowired
	private ReceveurRepository repository;
	
	@Autowired
	private SequenceGeneratorService service;
	

	@PostMapping("/addReceveur")
	public String saveReceveur(@RequestBody Receveur receveur) {
		receveur.setId(service.getSequenceNumber(Receveur.SEQUENCE_NAME));
		repository.save(receveur);
		return "Receveur ajoute avec id : " + receveur.getId();
	}

	@GetMapping("/findAllReceveurs")
	public List<Receveur> getReceveur() {
		return repository.findAll();
	}

	@GetMapping("/findAllReceveur/{id}")
	public Optional<Receveur> getReceveur(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/deleteReceveur/{id}")
	public String deleteReceveur(@PathVariable int id) {
		repository.deleteById(id);
		return "Receveur supprime avec id : " + id;
	}

}
