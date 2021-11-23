package cundi.edu.co.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.ILibroService;


@RestController
@RequestMapping("/libros")
public class LibroController {
	
	@Autowired
	private ILibroService libroService;
	
	@GetMapping(value = "/obtenerPaginado", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<LibroDto> results  = libroService.retornarPaginado(page);
		return new ResponseEntity<Page<LibroDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPorId/{id}", produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id) throws ModelNotFoundException {
		LibroDto libro = libroService.retonarPorId(id);
		return new ResponseEntity<LibroDto>(libro, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPorIdDetalle/{id}", produces = "application/json")
	public ResponseEntity<?> retonarPorIdDetalle(@PathVariable int id) throws ModelNotFoundException {
		LibroDto libro = libroService.retonarPorIdDetalle(id);
		return new ResponseEntity<LibroDto>(libro, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody LibroDto libro) throws ConflictException {
		libroService.guardar(libro);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody LibroDto libro)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		libroService.editar(libro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException {
		libroService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
