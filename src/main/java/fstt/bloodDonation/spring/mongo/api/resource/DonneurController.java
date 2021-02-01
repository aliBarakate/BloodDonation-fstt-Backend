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

import fstt.bloodDonation.spring.mongo.api.model.Donneur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;

@RestController
public class DonneurController {

	@Autowired
	private DonneurRepository repository;
	@Autowired
	private SequenceGeneratorService service;
	

	@PostMapping("/addDonneur")
	public String saveDonneur(@RequestBody Donneur donneur) {
		donneur.setId(service.getSequenceNumber(Donneur.SEQUENCE_NAME));
		repository.save(donneur);
		return "Donneur ajote avec id : " + donneur.getId();
	}

	@GetMapping("/findAllDonneurs")
	public List<Donneur> getDonneurs() {
		return repository.findAll();
	}

	@GetMapping("/findAllDonneurs/{id}")
	public Optional<Donneur> getDonneur(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/deleteDonneur/{id}")
	public String deleteDonneur(@PathVariable int id) {
		repository.deleteById(id);
		return "Donneur supprime avec id : " + id;
	}

}
