package fstt.bloodDonation.spring.mongo.api.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fstt.bloodDonation.spring.mongo.api.model.AuthenticationBean;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AuthContoller {
	
	@GetMapping(path="/basicauth")
	public AuthenticationBean helloWorldBean() {
	return new AuthenticationBean("You are authenticated");
	}
}
