package coop.tecso.exam.todo1.hulkstore.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.FranchiseDoesNotExistException;

@Service

public class FranchiseService {

	private FranchiseRepository repository;

	public FranchiseService(FranchiseRepository repository) {
		this.repository = repository;
	}
	
	public List<Franchise> findAllFranchises() {
		return repository.findAll();
	}
	
	public void checkIfFranchiseExists(String id) {
		Optional<Franchise> optional = repository.findById(id);
		if(!optional.isPresent()) {
			throw new FranchiseDoesNotExistException(id);
		}
	}
	
	public Franchise findById(String id) {
		Optional<Franchise> optional = repository.findById(id);
		if(!optional.isPresent()) {
			throw new FranchiseDoesNotExistException(id);
		}
		return optional.get();
	}
	
}
