package cundi.edu.co.demo.service.impl;

import javax.transaction.Transactional;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorEditorialRepo;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.repository.IEditorialRepo;
import cundi.edu.co.demo.service.IAutorEditorialService;

@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService {
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IAutorEditorialRepo repo;
	
	@Autowired
	private IEditorialRepo editorialRepo;
	
	@Autowired
	private IAutorRepo autorRepo;
	
	@Override
	public Page<AutorEditorialDto> retornarPaginado(int page, int size) { return null; }

	@Override
	public Page<AutorEditorialDto> retornarPaginado(Pageable page) {
		Page<AutorEditorialDto> results = repo.findAll(page).map(a -> mapper.map(a, AutorEditorialDto.class));
		results.forEach(x -> x.getAutor().setLibros(null));
		return results;
	}
	
	@Override
	public Page<AutorEditorialDto> buscarPorAutor(Integer idAutor, Pageable page) throws ModelNotFoundException {
		if (!autorRepo.existsById(idAutor)) throw new ModelNotFoundException("Autor no encontrado.");
		Page<AutorEditorialDto> results = repo.findByAutorId(idAutor, page).map(a -> mapper.map(a, AutorEditorialDto.class));
		results.forEach(x -> x.setAutor(null));
		return results;
	}

	@Override
	public Page<AutorEditorialDto> buscarPorEditorial(Integer idEditorial, Pageable page) throws ModelNotFoundException {
		if (!editorialRepo.existsById(idEditorial)) throw new ModelNotFoundException("Editorial no encontrada.");
		Page<AutorEditorialDto> results = repo.findByEditorialId(idEditorial, page).map(a -> mapper.map(a, AutorEditorialDto.class));
		results.forEach(x -> { x.setEditorial(null); x.getAutor().setLibros(null); });
		return results;
	}

	@Override
	public AutorEditorialDto retonarPorId(Integer idObj) throws ModelNotFoundException { return null; }

	@Override
	public void guardar(AutorEditorialDto obj) throws ConflictException {
		Integer idAutor = obj.getAutor().getId();
		Integer idEditorial = obj.getEditorial().getId();
		
		if (idAutor == null || !autorRepo.existsById(idAutor))
			throw new ConflictException("Autor no encontrado.");

		if (idEditorial == null || !editorialRepo.existsById(idEditorial))
			throw new ConflictException("Editorial no encontrada.");
		
		if (repo.existsByAutorAndEditorialNativa(idAutor, idEditorial))
			throw new ConflictException("La relación entre autor y editorial ya existe.");
		
		this.repo.guardarNativo(idAutor, idEditorial, obj.getFecha());
	}

	@Override
	public void editar(AutorEditorialDto obj) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {}
	
	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {}

	@Override
	public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
		if (!repo.existsByAutorAndEditorialNativa(idAutor, idEditorial))
			throw new ModelNotFoundException("La relación entre autor y editorial no fue encontrada.");
		this.repo.eliminarNativa(idAutor, idEditorial);
	}

	@Transactional
	@Override
	public void asociarAutorEditorial() {
		this.repo.guardarNativo(1, 1, LocalDate.now());
		this.repo.guardarNativo(2, 1, LocalDate.now());
		this.repo.guardarNativo(8, 1, LocalDate.now());
		this.repo.guardarNativo(6, 1, LocalDate.now());
	}

}
