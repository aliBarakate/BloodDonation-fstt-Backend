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

import fstt.bloodDonation.spring.mongo.api.model.Medecin;
import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.MedecinRepository;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;

@RestController
public class MedecinController {

	@Autowired
	private MedecinRepository repository;

	@PostMapping("/addMedecin")
	public String saveMedecin(@RequestBody Medecin medecin) {
		repository.save(medecin);
		return "Medecin ajoute avec id : " + medecin.getId();
	}

	@GetMapping("/findAllMedecins")
	public List<Medecin> getMedecin() {
		return repository.findAll();
	}

	@GetMapping("/findAllMedecin/{id}")
	public Optional<Medecin> getMedecin(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/deleteMedecin/{id}")
	public String deleteMedecin(@PathVariable int id) {
		repository.deleteById(id);
		return "Medecin supprime avec id : " + id;
	}

}
