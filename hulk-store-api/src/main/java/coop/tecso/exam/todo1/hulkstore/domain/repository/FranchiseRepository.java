package coop.tecso.exam.todo1.hulkstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, String> {

}
