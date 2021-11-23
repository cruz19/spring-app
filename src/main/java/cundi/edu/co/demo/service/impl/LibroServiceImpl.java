package cundi.edu.co.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.repository.ILibroRepo;
import cundi.edu.co.demo.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ILibroRepo libroRepo;
	
	@Autowired
	private IAutorRepo autorRepo;

	@Override
	public Page<LibroDto> retornarPaginado(int page, int size) { return null; }

	@Override
	public Page<LibroDto> retornarPaginado(Pageable page) {
		Page<LibroDto> libros = libroRepo.findAll(page).map(a -> modelMapper.map(a, LibroDto.class));
		libros.forEach(l -> l.setAutor(null));
		return libros;
	}

	@Override
	public LibroDto retonarPorId(Integer id) throws ModelNotFoundException {
		Libro libro = libroRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
		LibroDto dto = modelMapper.map(libro, LibroDto.class);
		dto.setAutor(null);
		return dto;
	}
	
	@Override
	public LibroDto retonarPorIdDetalle(Integer id) throws ModelNotFoundException {
		Libro libro = libroRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
		LibroDto dto = modelMapper.map(libro, LibroDto.class);
		return dto;
	}

	@Override
	public void guardar(LibroDto dto) throws ConflictException {
		Libro libro = modelMapper.map(dto, Libro.class);
		if (libroRepo.existsByNombre(libro.getNombre())) {
			throw new ConflictException("Ya existe un libro con ese nombre.");
		}
		if (!autorRepo.existsById(libro.getAutor().getId())) {
			throw new ConflictException("No existe un autor con el id enviado");
		}
		
		libroRepo.save(libro);
	}

	@Override
	public void editar(LibroDto dto) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		if (dto.getId() == null)
			throw new ArgumentRequiredException("ID libro es requerido");
		
		if (!libroRepo.existsById(dto.getId()))
			throw new ModelNotFoundException("Libro no encontrado");
		
		if (libroRepo.existsByNombreToUpdate(dto.getNombre(), dto.getId()))
			throw new ConflictException("El nombre ya está siendo utilizado por otro libro");
		
		if (!autorRepo.existsById(dto.getAutor().getId()))
			throw new ConflictException("No existe un autor con el id enviado");

		Libro libro = modelMapper.map(dto, Libro.class);

		libroRepo.save(libro);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		if (!libroRepo.existsById(id))
			throw new ModelNotFoundException("Libro no encontrado");

		libroRepo.deleteById(id);
	}
	
}
