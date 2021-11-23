package cundi.edu.co.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IAutorEditorialService  extends ICrud<AutorEditorialDto, Integer> {
	public Page<AutorEditorialDto> buscarPorAutor(Integer idAutor, Pageable page) throws ModelNotFoundException;
	public Page<AutorEditorialDto> buscarPorEditorial(Integer idEditorial, Pageable page) throws ModelNotFoundException;
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;
	public void asociarAutorEditorial();
}