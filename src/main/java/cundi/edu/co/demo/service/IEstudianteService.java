package cundi.edu.co.demo.service;

import java.util.List;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IEstudianteService extends ICrud<Estudiante, Integer>{

	public List<Estudiante> retornarTodo();
		
	public EstudianteDto retornar(int i) throws ModelNotFoundException, Exception;
	
}