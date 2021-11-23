package cundi.edu.co.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Autor;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer> {
	public Boolean existsByCedula(String cedula);
	public Boolean existsByCorreo(String correo);
	public Autor findByLibrosNombre(String nombre);

	// NATIVE SQL
	@Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Autor a WHERE a.cedula = :cedula AND a.id <> :id", nativeQuery = true)
	public Boolean existsByCedulaToUpdate(@Param("cedula") String cedula, @Param("id") Integer id);
	
	@Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Autor a WHERE a.correo = :correo AND a.id <> :id", nativeQuery = true)
	public Boolean existsByCorreoToUpdate(@Param("correo") String correo, @Param("id") Integer id);
	
	// JPQL
	@Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
	public Page<Autor> containsNombre(@Param("nombre") String nombre, Pageable pageable);
	
	// FindBy Property
	public Autor findByCorreo(String correo);
	public Autor findByCedula(String cedula);
	
	// FindBy PropertyAndPropery
	public Autor findByCedulaAndCorreo(String cedula, String correo);

	// FindBy Ending
	public Page<Autor> findByCorreoEndingWith(String correo, Pageable pageable);
	
	// FindBy Containing
	public Page<Autor> findByCorreoContaining(String correo, Pageable pageable);

	// FindBy OrderByDesc
	public Page<Autor> findByOrderByNombreDesc(Pageable pageable);
}
