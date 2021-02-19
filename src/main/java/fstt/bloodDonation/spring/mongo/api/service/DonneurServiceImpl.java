package fstt.bloodDonation.spring.mongo.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


import fstt.bloodDonation.spring.mongo.api.model.Donneur;
import fstt.bloodDonation.spring.mongo.api.model.Rdv;
import fstt.bloodDonation.spring.mongo.api.model.Receveur;
import fstt.bloodDonation.spring.mongo.api.repository.DonneurRepository;
import fstt.bloodDonation.spring.mongo.api.repository.RdvRepository;
import fstt.bloodDonation.spring.mongo.api.repository.ReceveurRepository;

@Service
public class DonneurServiceImpl implements DonneurService {
	
	@Autowired
	private DonneurRepository repository;
	@Autowired
	private SequenceGeneratorService service;
	
	@Autowired
	private ReceveurRepository Receveurrepository;
	
	@Autowired
	private RdvRepository Rdvrepository;
	
	@Autowired
	private SequenceGeneratorService Rdvservice;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("blooddonationmorocco@gmail.com");
	    mailSender.setPassword("blood123donation");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    
	    return mailSender;
	}
	
	public void fctMail(String mail,String textMail,String sujet)
	{
		System.out.println("sending mail");		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom("java-master@gmail.com");
		simpleMailMessage.setTo(mail);
		simpleMailMessage.setSubject(sujet);
		simpleMailMessage.setText(textMail);
		javaMailSender.send(simpleMailMessage);
		System.out.println("sent mail");
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	@Override
	public String saveDonneur(Donneur donneur) {
		donneur.setId(service.getSequenceNumber(Donneur.SEQUENCE_NAME));
		donneur.setActif(1);
		donneur.setCompteur(0);
		repository.save(donneur);
		
		int idRdv=Rdvservice.getSequenceNumber(Rdv.SEQUENCE_NAME);
		String lieuRdv="Centre National de Transfusion Sanguine de "+donneur.getVille();		
		Date dateRdv=new Date();		
		int mois=dateRdv.getMonth()+1;
		if(mois==13)mois=01;
		dateRdv.setMonth(mois);
		Rdv rdv=new Rdv(idRdv, dateRdv, lieuRdv, donneur);		
		Rdvrepository.save(rdv);
		

		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
	    String date=formater.format(dateRdv);
	    
	    String textMail="Cher/Chère volantaire"
				+ "\n Nous avons reçu votre demande d'inscription pour faire "
				+ "partie de cette merveilleuse communaute.\n"
				+ "Afin de completer votre inscription\n "
				+ "Nous vous donnons rendez-vous le  "+date
				+"  au  "+lieuRdv
				+ "  pour faire quelques analyses gratuitement. "
				
				+ "\n Merci d'aider à sauver une vie ,merci d'offrir votre sang !\n";
	    
	    String sujet="BloodDonation_Rendez_vous";
		
	    fctMail(donneur.getMail(),textMail,sujet);
	    
		return "Donneur ajoute avec id : " + donneur.getId();
	}

	@Override
	public List<Donneur> getDonneurs() {
		return repository.findAll();
	}
	
	
	
	//findByVilleAndGroupeSanguinAndActifOrderByCompteur
	
	@Override
	public List<Donneur> getDonneursForReceveur(String ville,String groupeSanguin) {
		List<Donneur> listDonneur=new ArrayList<Donneur>();
		listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville, groupeSanguin,1);
		
		if(listDonneur.isEmpty())
		{
			switch (groupeSanguin) {
			
			case "O_neg":
				return listDonneur;
			
			case "O_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "A_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "A_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);
				
				if(listDonneur.isEmpty())	{		
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				}
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
				
			case "B_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "B_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
				
			case "AB_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
						
			case "AB_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"AB_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_pos",1);

				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);				
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_pos",1);
						
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
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
	MongoTemplate mongoTemplate;

	@Override
	public void SendMail(String mail)
	{
		
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		query.limit(1);
		Receveur receveur = mongoTemplate.findOne(query, Receveur.class);
				
		
		
		String textMail="Cher/Chère volantaire"
				+ "\n Nous venons de recevoir un cas d'urgence "
				+ "qui a besoin de votre don de sang.\n "
				+ "Ce cas est hospitalisé à l'hopital "+receveur.getHopital()+" .\n"
				+ "Adresse:"+ receveur.getAdresse() +" .\n"
				+ "\n Veuillez repondre le plus tot possible à cette demande .\n"				
				+ "Merci d'aider à sauver une vie ,merci d'offrir votre sang\n";
		
		String sujet="BloodDonation_Appel d'urgence";
		
		fctMail(mail,textMail,sujet);
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
		d.setActif(1);
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
		DonneurUpdated.setActif(d.getActif());
		DonneurUpdated.setDate_derniere_donation(d.getDate_derniere_donation());
		repository.save(DonneurUpdated);
		
		return "Donneur mis a jour";
	}

	@Override
	public String updateInactifDonneur() {
		// TODO Auto-generated method stub
		
		List<Donneur> listdonneur=repository.findByActif(0);
		for(Donneur d:listdonneur)
		{
			Date dateDonnation=d.getDate_derniere_donation();	
			if(!dateDonnation.toString().isEmpty())
			{
				Date aujourdhui=new Date();
				int mois=dateDonnation.getMonth()+1;
				int annee=dateDonnation.getYear();
				if(mois==13){ mois=01; annee++;}
				dateDonnation.setMonth(mois);
				dateDonnation.setYear(annee);
				if(aujourdhui.after(dateDonnation)) 
				{
					d.setActif(1);
					repository.save(d);
				}
			}
		}
		
		return "Les donneurs inactifs sont mis a jours";
	}

	@Override
	public List<Donneur> getDonneursForReceveur() {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		query.limit(1);
		Receveur receveur = mongoTemplate.findOne(query, Receveur.class);
		String ville=receveur.getVille();
		String groupeSanguin=receveur.getGroupeSanguin();
		
		List<Donneur> listDonneur=new ArrayList<Donneur>();
		listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville, groupeSanguin,1);
		
		if(listDonneur.isEmpty())
		{
			switch (groupeSanguin) {
			
			case "O_neg":
				return listDonneur;
			
			case "O_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "A_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "A_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);
				
				if(listDonneur.isEmpty())	{		
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				}
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
				
			case "B_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;		
				
			case "B_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
				
			case "AB_neg":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;				
						
			case "AB_pos":
				listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"AB_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_pos",1);

				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"A_neg",1);				
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_pos",1);
						
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"B_neg",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_pos",1);
				
				if(listDonneur.isEmpty())			
					listDonneur=repository.findByVilleAndGroupeSanguinAndActifOrderByCompteur(ville,"O_neg",1);
				return listDonneur;			

			}		
			
		}
	
		return listDonneur;
		

	}

	@Override
	public void demandeAccepte(String mail) {
		// TODO Auto-generated method stub
		
		Donneur d=repository.findByMail(mail);
		d.setActif(0);
		d.setDate_derniere_donation(new Date());
		d.setCompteur(d.getCompteur()+1);
		repository.save(d);
		
	}

	@Override
	public void ActiverDonneur(int id) {
		// TODO Auto-generated method stub
		Donneur d=repository.findById(id).get();
		d.setActif(1);
		repository.save(d);
		
	}
	
	
	
	
	
}
