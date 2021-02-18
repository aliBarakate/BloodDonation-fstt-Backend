package fstt.bloodDonation.spring.mongo.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;
import fstt.bloodDonation.spring.mongo.api.repository.MedecinRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	MedecinRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Medecin>user=userRepository.findByLogin(login);
		
		user.orElseThrow(()->new UsernameNotFoundException("Not found :"+login));
		
		//return new MyUserDetails(user);
		return user.map(MyUserDetails::new).get();
	}

}
