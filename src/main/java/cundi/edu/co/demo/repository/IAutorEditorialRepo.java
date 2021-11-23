package cundi.edu.co.demo.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.AutorEditorial;

@Repository
public interface IAutorEditorialRepo extends JpaRepository<AutorEditorial, Integer> {
	//Para INSERT UPDATE Y DELETE DEBEN COLOCAR EL @Transactional Y EL @Modifying
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_editorial(id_autor, id_editorial, fecha) VALUES(:idAutor, :idEditorial, :fecha)"
				,nativeQuery = true)
	void guardarNativo(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial, @Param("fecha") LocalDate fecha);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM autor_editorial where id_autor = :idAutor and id_editorial = :idEditorial ",  nativeQuery = true)
	void eliminarNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
	
	@Query(value = "SELECT CASE WHEN COUNT(x) > 0 THEN true ELSE false END FROM AutorEditorial x WHERE x.autor.id = :idAutor AND x.editorial.id = :idEditorial")
	Boolean existsByAutorAndEditorialNativa(@Param("idAutor") Integer idAutor, @Param("idEditorial") Integer idEditorial);
	
	public Page<AutorEditorial> findByAutorId(Integer idAutor, Pageable pageable);
	
	public Page<AutorEditorial> findByEditorialId(Integer idAutor, Pageable pageable);
}
