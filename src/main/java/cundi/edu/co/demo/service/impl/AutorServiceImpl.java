package cundi.edu.co.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAutorRepo autorRepo;

	@Override
	public Page<AutorDto> retornarPaginado(int page, int size) {
		Page<AutorDto> autores = autorRepo.findAll(PageRequest.of(page, size)).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

	@Override
	public Page<AutorDto> retornarPaginado(Pageable page) {
		Page<AutorDto> autores = autorRepo.findAll(page).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

	@Override
	public AutorDto retonarPorId(Integer id) throws ModelNotFoundException {
		Autor autor = autorRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
		autorDto.setLibros(null);
		return autorDto;
	}
	
	@Override
	public AutorDto retonarPorIdDetalle(Integer id) throws ModelNotFoundException {
		Autor autor = autorRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Autor no encontrado"));
		AutorDto dto = modelMapper.map(autor, AutorDto.class);
		return dto;
	}

	@Override
	public void guardar(AutorDto autorDto) throws ConflictException {
		Autor autor = modelMapper.map(autorDto, Autor.class);
		
		if (autorRepo.existsByCedula(autor.getCedula()))
			throw new ConflictException("La cédula ya está siendo utilizada por otro autor");

		if (autorRepo.existsByCorreo(autor.getCorreo()))
			throw new ConflictException("El correo ya está siendo utilizado por otro autor");

		if (autor.getLibros() != null)
			autor.getLibros().stream().forEach(libro -> libro.setAutor(autor));
		autorRepo.save(autor);
	}

	@Override
	public void editar(AutorDto autor) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {		
		if (autor.getId() == null)
			throw new ArgumentRequiredException("ID autor es requerido");

		if (!autorRepo.existsById(autor.getId()))
			throw new ModelNotFoundException("Autor no encontrado");

		if (autorRepo.existsByCedulaToUpdate(autor.getCedula(), autor.getId()))
			throw new ConflictException("La cédula ya está siendo utilizada por otro autor");
		
		if (autorRepo.existsByCorreoToUpdate(autor.getCorreo(), autor.getId()))
			throw new ConflictException("El correo ya está siendo utilizado por otro autor");
		
		Autor autorEntity = autorRepo.getById(autor.getId());
		autorEntity.setNombre(autor.getNombre());
		autorEntity.setApellido(autor.getApellido());
		autorEntity.setCedula(autor.getCedula());
		autorEntity.setCorreo(autor.getCorreo());

		autorRepo.save(autorEntity);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		if (!autorRepo.existsById(id))
			throw new ModelNotFoundException("Autor no encontrado");

		autorRepo.deleteById(id);
	}
	
	@Override
	public AutorDto buscarPorNombreLibro(String nombre) throws ModelNotFoundException {
		Autor autor = autorRepo.findByLibrosNombre(nombre);
		if (autor == null) throw new ModelNotFoundException("No se encontró ningún autor por el nombre de libro enviado.");
		return modelMapper.map(autor, AutorDto.class);
	}

	/// CONSULTAS findBy | existsBy | contains | termina
	////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<AutorDto> contieneNombre(String nombre, Pageable page) {
		Page<AutorDto> autores = autorRepo.containsNombre(nombre, page).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

	@Override
	public AutorDto buscarPorCorreo(String correo) throws ModelNotFoundException {
		Autor autor = autorRepo.findByCorreo(correo);
		if (autor == null) throw new ModelNotFoundException("Autor no encontrado");
		AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
		autorDto.setLibros(null);
		return autorDto;
	}

	@Override
	public AutorDto buscarPorCedula(String cedula) throws ModelNotFoundException {
		Autor autor = autorRepo.findByCedula(cedula);
		if (autor == null) throw new ModelNotFoundException("Autor no encontrado");
		AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
		autorDto.setLibros(null);
		return autorDto;
	}

	@Override
	public AutorDto buscarPorCedulaYCorreo(String cedula, String correo) throws ModelNotFoundException {
		Autor autor = autorRepo.findByCedulaAndCorreo(cedula, correo);
		if (autor == null) throw new ModelNotFoundException("Autor no encontrado");
		AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
		autorDto.setLibros(null);
		return autorDto;
	}

	@Override
	public Page<AutorDto> correoTerminaEn(String correo, Pageable pageable) {
		Page<AutorDto> autores = autorRepo.findByCorreoEndingWith(correo, pageable).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

	@Override
	public Page<AutorDto> contieneCorreo(String correo, Pageable pageable) {
		Page<AutorDto> autores = autorRepo.findByCorreoContaining(correo, pageable).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

	@Override
	public Page<AutorDto> obtenerPaginadoOrdenarPorNombreDesc(Pageable pageable) {
		Page<AutorDto> autores = autorRepo.findByOrderByNombreDesc(pageable).map(a -> modelMapper.map(a, AutorDto.class));
		autores.forEach(a -> a.setLibros(null));
		return autores;
	}

}