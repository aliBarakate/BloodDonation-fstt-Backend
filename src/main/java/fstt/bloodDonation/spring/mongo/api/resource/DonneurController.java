package fstt.bloodDonation.spring.mongo.api.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;
import fstt.bloodDonation.spring.mongo.api.service.DonneurService;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;

@RestController
public class DonneurController {

	
	@Autowired
	private DonneurService donneurService;
	
	

	

	@GetMapping("/findDonneurbyCin/{cin}")
	public Donneur getByCin(@PathVariable String cin) {
		return donneurService.getByCin(cin);
	}

	@PostMapping("/addDonneur")
	public String saveDonneur(@RequestBody Donneur donneur) {
		return donneurService.saveDonneur(donneur);
	}
	
	@GetMapping("/findAllDonneurs")
	public List<Donneur> getDonneurs() {
		return donneurService.getDonneurs();
	}
	
	@GetMapping("/findAllDonneursForReceveur")
	public List<Donneur> getDonneursForReceveur(@RequestParam(name="ville")String ville,@RequestParam(name = "grpSanguin")String groupeSanguin) {		
		return donneurService.getDonneursForReceveur(ville, groupeSanguin);
	}
	
	@GetMapping("/findAllDonneurs/{id}")
	public Optional<Donneur> getDonneur(@PathVariable int id) {
		return donneurService.getDonneur(id);
	}
	
	@DeleteMapping("/deleteDonneur/{id}")
	public String deleteDonneur(@PathVariable int id) {
		return donneurService.deleteDonneur(id);
	}
	
	@GetMapping("/sendmail")
	public void Sendmail(@RequestParam(name="mail")String mail) {
		donneurService.SendMail(mail);
	}
	
	@DeleteMapping("/deleteByCin/{cin}")
	public String deleteByCin(@PathVariable String cin) {
		return donneurService.deleteByCin(cin);
	}
	
	@PutMapping("/updateDonneurByCin")
	public String updateDonneurByCin(@RequestParam(name="cin")String cin,
								@RequestParam(name="grpSanguin") String grpSanguin) {
		return donneurService.updateDonneurByCin(cin, grpSanguin);
	}
	
	@PutMapping("/updateDonneur")
	public String updateDonneur(@RequestBody Donneur d) {
		return donneurService.updateDonneur(d);
	}
	
	@PutMapping("/updateInactifDonneurs")
	public String updateInactifDonneur() {
		return donneurService.updateInactifDonneur();
	}
	
	
}
