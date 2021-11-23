package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Estudiante;

@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer> {
	
	
	public Estudiante findByCedula(String cedula);
	
	public Estudiante findByCorreo(String correo);
	
	public Estudiante findByApellido(String apellido);
	
	public Boolean existsByCedula(String cedula);
	
	public Boolean existsByCorreo(String correo);
	
	//JPQL
	
	//SQL
	
	//correo existe sin tener en cuenta el propio
	
	// findBy todas las opciones
	
}