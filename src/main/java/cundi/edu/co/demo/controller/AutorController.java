package cundi.edu.co.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IAutorEditorialService;
import cundi.edu.co.demo.service.IAutorService;

// @PreAuthorize("hasAuthority('Administrador')")
@RestController
@RequestMapping("/autores")
// @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AutorController {
	
	@Autowired
	private IAutorService autorService;
	
	@Autowired
	private IAutorEditorialService serviceAE;
	
	// @PreAuthorize("hasAuthority('Administrador')  OR hasAuthority('Vendedor') ")
	@GetMapping(value = "/obtenerPaginado", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<AutorDto> results  = autorService.retornarPaginado(page);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<AutorDto> results  = autorService.retornarPaginado(page, size);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
	// @PreAuthorize("hasAuthority('Administrador')  OR hasAuthority('Vendedor') ")
	@GetMapping(value = "/obtenerPorId/{id}", produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id) throws ModelNotFoundException {
		AutorDto autor = autorService.retonarPorId(id);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPorIdDetalle/{id}", produces = "application/json")
	public ResponseEntity<?> retonarPorIdDetalle(@PathVariable int id) throws ModelNotFoundException {
		AutorDto autor = autorService.retonarPorIdDetalle(id);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody AutorDto autor) throws ConflictException {
		autorService.guardar(autor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody AutorDto autor)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		autorService.editar(autor);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id) throws ModelNotFoundException {
		autorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value = "/asociarEditorial", consumes = "application/json")
	public ResponseEntity<?> asociarEditorail(@Valid @RequestBody AutorEditorialDto autorEditorial) throws ConflictException {
		serviceAE.guardar(autorEditorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/desasociarEditorial/{idAutor}/{idEditorial}")
	public ResponseEntity<?> desasociarEditorail(@PathVariable int idAutor, @PathVariable int idEditorial) throws ModelNotFoundException {
		serviceAE.eliminarNativo(idAutor, idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/buscarPorNombreLibro/{nombre}", produces = "application/json")
	public ResponseEntity<?> buscarPorNombreLibro(@PathVariable String nombre) throws ModelNotFoundException {
		AutorDto autor = autorService.buscarPorNombreLibro(nombre);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerEditoriales/{idAutor}", produces = "application/json")
	public ResponseEntity<?> contieneNombre(@PathVariable Integer idAutor, Pageable page) throws ModelNotFoundException {
		Page<AutorEditorialDto> results  = serviceAE.buscarPorAutor(idAutor, page);
		return new ResponseEntity<Page<AutorEditorialDto>>(results, HttpStatus.OK);
	}
	
	/// CONSULTAS...
	////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping(value = "/contieneNombre/{nombre}", produces = "application/json")
	public ResponseEntity<?> contieneNombre(@PathVariable String nombre, Pageable page) {
		Page<AutorDto> results  = autorService.contieneNombre(nombre, page);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorCorreo/{correo}", produces = "application/json")
	public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) throws ModelNotFoundException {
		AutorDto autor  = autorService.buscarPorCorreo(correo);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorCedula/{cedula}", produces = "application/json")
	public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) throws ModelNotFoundException {
		AutorDto autor  = autorService.buscarPorCedula(cedula);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorCedulaYCorreo/{cedula}/{correo}", produces = "application/json")
	public ResponseEntity<?> buscarPorCedulaYCedula(@PathVariable String cedula, @PathVariable String correo) throws ModelNotFoundException {
		AutorDto autor  = autorService.buscarPorCedulaYCorreo(cedula, correo);
		return new ResponseEntity<AutorDto>(autor, HttpStatus.OK);
	}

	@GetMapping(value = "/correoTerminaEn/{correo}", produces = "application/json")
	public ResponseEntity<?> correoTerminaEn(@PathVariable String correo, Pageable page) {
		Page<AutorDto> results = autorService.correoTerminaEn(correo, page);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/contieneCorreo/{correo}", produces = "application/json")
	public ResponseEntity<?> contieneCorreo(@PathVariable String correo, Pageable page) {
		Page<AutorDto> results = autorService.contieneCorreo(correo, page);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
	@GetMapping(value = "/obtenerPaginadoOrdenarPorNombreDesc", produces = "application/json")
	public ResponseEntity<?> obtenerPaginadoOrdenarPorNombreDesc(Pageable page) {
		Page<AutorDto> results = autorService.obtenerPaginadoOrdenarPorNombreDesc(page);
		return new ResponseEntity<Page<AutorDto>>(results, HttpStatus.OK);
	}
	
}



