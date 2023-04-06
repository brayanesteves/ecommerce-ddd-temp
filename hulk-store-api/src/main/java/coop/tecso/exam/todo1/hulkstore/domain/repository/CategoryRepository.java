package coop.tecso.exam.todo1.hulkstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
