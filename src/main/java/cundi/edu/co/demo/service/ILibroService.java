package cundi.edu.co.demo.service;

import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface ILibroService extends ICrud<LibroDto, Integer> {
	public LibroDto retonarPorIdDetalle(Integer id) throws ModelNotFoundException;
}
