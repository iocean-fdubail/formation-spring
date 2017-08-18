package fr.iocean.dta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.iocean.dta.user.model.User;
import fr.iocean.dta.user.repository.UserRepository;

@Component
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		Optional<User> result = userRepository.findOneByLogin(username);
		if (result.isPresent()) {
			User user = result.get();
			List<GrantedAuthority> rules = this.getUserCredentials(user);
			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
		}

		throw new UsernameNotFoundException("username.not.found");
	}

	private List<GrantedAuthority> getUserCredentials(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}
}