package fr.iocean.dta.authentication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			List<GrantedAuthority> rules = this.getGrantedAuthorities(user);
			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
		}
		throw new UsernameNotFoundException("username.not.found");
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
			return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
			}
}