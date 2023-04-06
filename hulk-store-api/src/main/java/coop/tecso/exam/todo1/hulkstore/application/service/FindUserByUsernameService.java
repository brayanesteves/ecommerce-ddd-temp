package coop.tecso.exam.todo1.hulkstore.application.service;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.UserDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.User;
import coop.tecso.exam.todo1.hulkstore.domain.service.UserService;

@Service

public class FindUserByUsernameService {

	private UserService userService;

	public FindUserByUsernameService(UserService userService) {
		this.userService = userService;
	}

	public UserDto execute(String username) {
		
		User user = userService.findByUsername(username);
		
		return UserDto.fromModel(user);
	}
	
}
