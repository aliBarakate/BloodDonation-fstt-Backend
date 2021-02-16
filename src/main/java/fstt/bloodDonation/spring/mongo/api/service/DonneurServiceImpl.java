package fstt.bloodDonation.spring.mongo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fstt.bloodDonation.spring.mongo.api.model.Donneur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;

@Service
public class DonneurServiceImpl implements DonneurService {
	
	@Autowired
	private DonneurRepository repository;
	@Autowired
	private SequenceGeneratorService service;
	

	@Override
	public String saveDonneur(Donneur donneur) {
		donneur.setId(service.getSequenceNumber(Donneur.SEQUENCE_NAME));
		repository.save(donneur);
		return "Donneur ajote avec id : " + donneur.getId();
	}

	@Override
	public List<Donneur> getDonneurs() {
		return repository.findAll();
	}
	
	@Override
	public List<Donneur> getDonneursForReceveur(String ville,String groupeSanguin) {
		List<Donneur> listDonneur=new ArrayList<Donneur>();
		listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville, groupeSanguin);
		
		if(listDonneur.isEmpty())
		{
			switch (groupeSanguin) {
			
			case "O_neg":
				return listDonneur;
			
			case "O_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "A_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "A_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");
				
				if(listDonneur.isEmpty())	{		
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				}
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
				
			case "B_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;		
				
			case "B_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
				
			case "AB_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;				
						
			case "AB_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"AB_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_pos");

				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"A_neg");				
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_pos");
						
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"B_neg");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_pos");
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinOrderByCompteur(ville,"O_neg");
				return listDonneur;			

			}		
			
		}
	
		return listDonneur;
		
	}
	

	@Override
	public Optional<Donneur> getDonneur(int id) {
		return repository.findById(id);
	}

	@Override
	public String deleteDonneur( int id) {
		repository.deleteById(id);
		return "Donneur supprime avec id : " + id;
	}
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void SendMail(String mail)
	{
		System.out.println("sending mail");
		String textMail="Cher/Chère volantaire"
				+ "\n Nous venons de recevoir un cas d'urgence "
				+ "qui a besoin de votre don de sang.\n "
				+ "Ce cas est hospitalisé à l'hopital xxxxxxx.\n"
				+ "Adresse:xxxxxxxxxxxxxx\n"
				+ "\n Veuillez repondre le plus tot possible à cette demande .\n"				
				+ "Merci d'aider à sauver une vie ,merci d'offrir votre sang\n";
		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom("java-master@gmail.com");
		simpleMailMessage.setTo(mail);
		simpleMailMessage.setSubject("BloodDonation_Appel d'urgence");
		simpleMailMessage.setText(textMail);
		javaMailSender.send(simpleMailMessage);
		System.out.println("sent mail");
	}

	@Override
	public Donneur getByCin(String cin) {
		// TODO Auto-generated method stub
		return repository.findByCin(cin);
	}

	@Override
	public String deleteByCin(String cin) {
		// TODO Auto-generated method stub
		Donneur d=repository.findByCin(cin);
		int id=d.getId();
		repository.deleteById(id);
		return "Donneur supprime avec cin : " + cin;
		
	}

	@Override
	public String updateDonneurByCin (String cin,String grpSanguin) {
		// TODO Auto-generated method stub
		Donneur d=repository.findByCin(cin);
		d.setGroupeSanguin(grpSanguin);
		repository.save(d);
		return "Donneur avec cin : " + cin+" est mis a jour";
	}

	@Override
	public String updateDonneur(Donneur d) {
		// TODO Auto-generated method stub
		
		Donneur DonneurUpdated =repository.findById(d.getId()).get();
		DonneurUpdated.setCin(d.getCin());
		DonneurUpdated.setGenre(d.getGenre());
		DonneurUpdated.setMail(d.getMail());
		DonneurUpdated.setNom(d.getNom());
		DonneurUpdated.setPrenom(d.getPrenom());
		DonneurUpdated.setTel(d.getTel());
		DonneurUpdated.setVille(d.getVille());
		DonneurUpdated.setGroupeSanguin(d.getGroupeSanguin());
		repository.save(DonneurUpdated);
		
		return "Donneur mis a jour";
	}
	
	
}
