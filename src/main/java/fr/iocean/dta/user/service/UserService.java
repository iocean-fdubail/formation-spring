package fr.iocean.dta.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.iocean.dta.exception.NotFoundException;
import fr.iocean.dta.user.model.User;
import fr.iocean.dta.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}
	
	public User create(User user) {
		Assert.notNull(user, "Resource can't be null");
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User resource = userRepository.findOne(id);
		Assert.notNull(resource, "Resource can't be null");
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
}
