package fstt.bloodDonation.spring.mongo.api.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fstt.bloodDonation.spring.mongo.api.model.Medecin;


public class MyUserDetails  implements UserDetails {

	private Medecin myUser;
	
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails() {
		
	}

	public MyUserDetails(Medecin user) {
		
		myUser=new Medecin();
		myUser.setLogin(user.getLogin());
		myUser.setMdp(user.getMdp());
		this.authorities=Arrays.stream(user.getRoles().split(","))
		.map(SimpleGrantedAuthority::new)
		.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return myUser.getMdp();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return myUser.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

