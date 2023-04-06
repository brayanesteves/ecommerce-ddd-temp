package coop.tecso.exam.todo1.hulkstore.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.User;
import coop.tecso.exam.todo1.hulkstore.domain.repository.UserRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.UserDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.UsernameAlreadyExistsException;

@Service

public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User findByUsername(String username) {
		
		Optional<User> optional = userRepository.findByUsername(username);
		
		if(!optional.isPresent()) {
			throw new UserDoesNotExistException(username);
		}
		
		return optional.get();
		
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public void checkIfUsernameIsAvailable(String username) {
		
		Optional<User> optional = userRepository.findByUsername(username);
		
		if(optional.isPresent()) {
			throw new UsernameAlreadyExistsException(username);
		}
		
	}

}
