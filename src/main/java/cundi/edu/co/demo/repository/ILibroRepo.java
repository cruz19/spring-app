package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {
	public Boolean existsByNombre(String nombre);
	@Query(value = "SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Libro l WHERE l.nombre = :nombre AND l.id <> :id", nativeQuery = true)
	public Boolean existsByNombreToUpdate(@Param("nombre") String nombre, @Param("id") Integer id);
}
