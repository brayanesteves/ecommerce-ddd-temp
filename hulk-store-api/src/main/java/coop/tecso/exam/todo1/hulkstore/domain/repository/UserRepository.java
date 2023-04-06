package coop.tecso.exam.todo1.hulkstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.exam.todo1.hulkstore.domain.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public Optional<User> findByUsername(String username);

}
