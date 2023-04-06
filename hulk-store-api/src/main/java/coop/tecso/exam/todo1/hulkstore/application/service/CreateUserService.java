package coop.tecso.exam.todo1.hulkstore.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateUserRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.User;
import coop.tecso.exam.todo1.hulkstore.domain.service.UserService;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class CreateUserService {
	
	private UserService userService;

	public CreateUserService(UserService userService) {
		this.userService = userService;
	}

	public void execute(CreateUserRequest request) {
		
		FieldValidator.notNull(request, "CreateUserRequest");
		
		userService.checkIfUsernameIsAvailable(request.getUsername());
		
		String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
		
		User user = User.of(
				request.getId(), 
				request.getUsername(), 
				encodedPassword, 
				request.getFirstName(), 
				request.getLastName()
		);
		
		userService.save(user);
		
	}

}
