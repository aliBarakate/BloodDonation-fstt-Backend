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
import fstt.bloodDonation.spring.mongo.api.service.MedecinService;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;

@RestController
public class MedecinController {

	
	@Autowired
	private MedecinService medecinService;

	@PostMapping("/addMedecin")
	public String saveMedecin(@RequestBody Medecin medecin) {
		return medecinService.saveMedecin(medecin);
	}

	@GetMapping("/findAllMedecins")
	public List<Medecin> getMedecin() {
		return medecinService.getMedecin();
	}

	@GetMapping("/findAllMedecin/{id}")
	public Optional<Medecin> getMedecin(@PathVariable int id) {
		return medecinService.getMedecin(id);
	}

	@DeleteMapping("/deleteMedecin/{id}")
	public String deleteMedecin(@PathVariable int id) {
		return medecinService.deleteMedecin(id);
	}
	
	

}
