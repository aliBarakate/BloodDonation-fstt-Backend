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
import fstt.bloodDonation.spring.mongo.api.model.Rdv;
import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.MedecinRepository;
import fstt.bloodDonation.spring.mongo.api.repository.RdvRepository;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;

@RestController
public class RdvController {

	@Autowired
	private RdvRepository repository;

	@PostMapping("/addRdv")
	public String saveRdv(@RequestBody Rdv rdv) {
		repository.save(rdv);
		return "Rdv ajoute avec id : " + rdv.getId();
	}

	@GetMapping("/findAllRdvs")
	public List<Rdv> getRdv() {
		return repository.findAll();
	}

	@GetMapping("/findAllRdv/{id}")
	public Optional<Rdv> getRdv(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/deleteRdv/{id}")
	public String deleteRdv(@PathVariable int id) {
		repository.deleteById(id);
		return "Rdv supprime avec id : " + id;
	}

}
