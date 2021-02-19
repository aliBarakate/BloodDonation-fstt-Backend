package fstt.bloodDonation.spring.mongo.api.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fstt.bloodDonation.spring.mongo.api.service.MailRequest;
import fstt.bloodDonation.spring.mongo.api.service.MailResponse;
import fstt.bloodDonation.spring.mongo.api.service.DonneurService;
import fstt.bloodDonation.spring.mongo.api.service.EmailService;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;
import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.MedecinRepository;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;
import fstt.bloodDonation.spring.mongo.api.service.MedecinService;
import fstt.bloodDonation.spring.mongo.api.service.SequenceGeneratorService;
@CrossOrigin(origins = "http://localhost:4200")
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
	
	
	@Autowired
	private EmailService service;
	
	@Autowired
	MongoTemplate mongoTemplate;
	

	@GetMapping("/sendmail")
	//public MailResponse sendEmail(@RequestBody MailRequest request) {
	public MailResponse sendEmail(@RequestParam(name="mail")String gmail) {
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		query.limit(1);
		Receveur receveur = mongoTemplate.findOne(query, Receveur.class);
		
		
		Map<String, Object> model = new HashMap<>();
		model.put("Name", "blood donation");
		model.put("location", "morocco");
		model.put("hopital", receveur.getHopital());
		model.put("adr", receveur.getAdresse());
		model.put("mail", gmail);
		return service.sendEmail(gmail, model);

	}
	
	
	@Autowired
	private DonneurService donneurService;

	@GetMapping("/demandeAccepte")
	public String demandeAccepte(@RequestParam(name="mail") String mail) {
		donneurService.demandeAccepte(mail);
		return "Merci d'avoir accepté";
	}
	
	

}
