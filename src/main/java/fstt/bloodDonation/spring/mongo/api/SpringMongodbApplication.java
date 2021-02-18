package fstt.bloodDonation.spring.mongo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
		
	}
}
