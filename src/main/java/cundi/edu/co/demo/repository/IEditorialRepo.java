package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Editorial;

@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {
}
