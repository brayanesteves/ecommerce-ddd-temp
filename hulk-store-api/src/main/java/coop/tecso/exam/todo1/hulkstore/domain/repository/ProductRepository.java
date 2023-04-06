package coop.tecso.exam.todo1.hulkstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.exam.todo1.hulkstore.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	public Optional<Product> findByCode(String code);

}
