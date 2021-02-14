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
import fstt.bloodDonation.spring.mongo.api.service.RdvService;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;

@RestController
public class RdvController {


	@Autowired
	private RdvService rdvService;
	
	@PostMapping("/addRdv")
	public String saveRdv(@RequestBody Rdv rdv) {
		return rdvService.saveRdv(rdv);
	}
	
	@GetMapping("/findAllRdvs")
	public List<Rdv> getRdv() {
		return rdvService.getRdv();
	}

	@GetMapping("/findAllRdv/{id}")
	public Optional<Rdv> getRdv(@PathVariable int id) {
		return rdvService.getRdv(id);
	}

	@DeleteMapping("/deleteRdv/{id}")
	public String deleteRdv(@PathVariable int id) {
		return rdvService.deleteRdv(id);
	}
	


}
