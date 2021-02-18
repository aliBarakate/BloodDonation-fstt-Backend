
package fstt.bloodDonation.spring.mongo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//.antMatchers("/user").hasAnyRole("ADMIN","USER")
		
				//.antMatchers("/").permitAll()
				
				//.and().formLogin(); 
		
			/*	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**")
				.permitAll().anyRequest()
				.authenticated()
				.and				().httpBasic();
				//.antMatchers("/**").permitAll();
			*/	
				
		http.csrf().disable().authorizeRequests()
		
		
		//.antMatchers("/**").permitAll()
		//.antMatchers("/addMedecin").permitAll()
		//.antMatchers("/updateInactifDonneurs").hasRole("ADMIN")
		//.antMatchers("/deleteByCin/{cin}").hasRole("ADMIN")
		//.antMatchers("/findAllDonneurs").hasRole("ADMIN")
		
		.anyRequest().permitAll()
		.and				()//.formLogin();
		.httpBasic();
		
		
		/*
		
		 http.csrf()
         .disable()
       //  .exceptionHandling()
       //  .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
       //  })
       //  .and()
       //  .authenticationProvider(getProvider())
         .formLogin()
       //  .loginProcessingUrl("/login")
       //  .successHandler(new AuthentificationLoginSuccessHandler())
       //  .failureHandler(new SimpleUrlAuthenticationFailureHandler())
         .and()
         .logout()
         .logoutUrl("/logout")
       //  .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
         .invalidateHttpSession(true)
         .and()
         .authorizeRequests()
         .antMatchers("/**").permitAll()
        // .antMatchers("/logout").permitAll()
        // .antMatchers("/user").authenticated()
         .anyRequest().permitAll();
		
		*/
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return 	NoOpPasswordEncoder.getInstance();
	}
	

}