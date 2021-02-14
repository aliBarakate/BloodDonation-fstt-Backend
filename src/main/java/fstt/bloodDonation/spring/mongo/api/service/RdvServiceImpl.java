package fstt.bloodDonation.spring.mongo.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fstt.bloodDonation.spring.mongo.api.model.Rdv;
import fstt.bloodDonation.spring.mongo.api.repository.RdvRepository;

@Service
public class RdvServiceImpl implements RdvService{
	
	
	@Autowired
	private RdvRepository repository;
	
	
	@Autowired
	private SequenceGeneratorService service;


	@Override
	public String saveRdv(Rdv rdv) {
		rdv.setId(service.getSequenceNumber(Rdv.SEQUENCE_NAME));
		repository.save(rdv);
		return "Rdv ajoute avec id : " + rdv.getId();
	}


	@Override
	public List<Rdv> getRdv() 
		{
			return repository.findAll();
		}
	


	@Override
	public Optional<Rdv> getRdv(int id) {
		return repository.findById(id);
	}


	@Override
	public String deleteRdv(int id) {
		repository.deleteById(id);
		return "Rdv supprime avec id : " + id;
	}
	

	

}
