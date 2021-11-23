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

import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.dto.EditorialDto;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IAutorEditorialService;
import cundi.edu.co.demo.service.IEditorialService;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {
	@Autowired
	private IEditorialService editorialService;
	
	@Autowired
	private IAutorEditorialService serviceAE;
	
	@Autowired
	private IAutorEditorialService serviceAutorEditorial;
	
	@GetMapping(value = "/obtenerPaginado", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<EditorialDto> results  = editorialService.retornarPaginado(page);
		return new ResponseEntity<Page<EditorialDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPorId/{id}", produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id) throws ModelNotFoundException {
		EditorialDto editorial = editorialService.retonarPorId(id);
		return new ResponseEntity<EditorialDto>(editorial, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody EditorialDto editorial) throws ConflictException {
		editorialService.guardar(editorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody EditorialDto editorial)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		editorialService.editar(editorial);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException {
		editorialService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value = "/asociarAutores")
	public ResponseEntity<?> asociarAutores() {
		serviceAutorEditorial.asociarAutorEditorial();
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/obtenerAutores/{idEditorial}", produces = "application/json")
	public ResponseEntity<?> contieneNombre(@PathVariable Integer idEditorial, Pageable page) throws ModelNotFoundException {
		Page<AutorEditorialDto> results  = serviceAE.buscarPorEditorial(idEditorial, page);
		return new ResponseEntity<Page<AutorEditorialDto>>(results, HttpStatus.OK);
	}
}
