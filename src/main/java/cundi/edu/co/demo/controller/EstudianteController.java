package cundi.edu.co.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IEstudianteService;

@RestController
@RequestMapping("/estudiantes")
@Validated
public class EstudianteController {

	@Autowired
	private IEstudianteService service;
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener" ,produces = "application/json")
	public ResponseEntity<?> retonar() {
		List<Estudiante> listaEstudiante = service.retornarTodo();
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorId/{idEstudiante}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int idEstudiante) throws ModelNotFoundException {
		Estudiante estudainte = service.retonarPorId(idEstudiante);
		return new ResponseEntity<Estudiante>(estudainte, HttpStatus.OK);	
	}		
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener/{i}" ,produces = "application/json")
	public ResponseEntity<?> retonar(@PathVariable int i) throws ModelNotFoundException, Exception {
		EstudianteDto estudiante;
	    estudiante = service.retornar(i);
		return new ResponseEntity<EstudianteDto>(estudiante, HttpStatus.OK);	
		//return ResponseEntity.ok(est);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Estudiante estudiante) throws ConflictException {
		service.guardar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Estudiante estudiante)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	//204
	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/eliminarHeader/{i}")
	public ResponseEntity<?> eliminarConHeader(@PathVariable int i) {
		EstudianteDto est  = new EstudianteDto("Johans", "Gonzalez " + i);
		HttpHeaders header = new HttpHeaders();
		header.add("info1", "valor 1");
		header.add("info2", "valor 2");
		return new ResponseEntity<Object>(header, HttpStatus.NO_CONTENT);
	}	
	
	// @Autowired
	// private IEstudianteService estudianteService;
	
	// private final EstudianteModelAssembler assembler;
	
	// public EstudianteController(EstudianteModelAssembler assembler) {
	// 	this.assembler = assembler;
	// }
	
	// @ApiOperation(value = "Listar estudiantes", notes = "Se encarga de listar los estudiantes")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente", response = ExampleDto.class, responseContainer = "List" ),
    //     @ApiResponse(code = 400, message = "BAD REQUEST. La solicitud fue malformada, es posible que los datos no estén en el formato correcto", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 401, message = "UNAUTHORIZED. Usuario no autorizado para realizar esta petición", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 404, message = "NOT FOUND. El recurso no fue encontrado", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado en el sistema", response = ExceptionWrapper.class)
    // })
	// @GetMapping(value = "/listar", produces = "application/json")
	// public ResponseEntity<List<EntityModel<?>>> listar(){
	// 	List<EntityModel<?>> modelEntities = estudianteService.listar().stream().map(assembler::toModel).collect(Collectors.toList());
	// 	return new ResponseEntity<List<EntityModel<?>>>(modelEntities, HttpStatus.OK);
	// }

	// @ApiOperation(value = "Búsqueda de estudiante por ID", notes = "Se encarga de la búsqueda de estudiante por su id")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente", response = ExampleDto.class ),
    //     @ApiResponse(code = 400, message = "BAD REQUEST. La solicitud fue malformada, es posible que los datos no estén en el formato correcto", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 401, message = "UNAUTHORIZED. Usuario no autorizado para realizar esta petición", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 404, message = "NOT FOUND. El recurso no fue encontrado", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado en el sistema", response = ExceptionWrapper.class)
    // })
	// @GetMapping(value = "/obtener/{id}", produces = "application/json")
	// public ResponseEntity<EntityModel<?>> retornar(
	// 		@ApiParam( name = "id", type = "string", value = "ID del estudiante", example = "7", required = true )
	// 		@PathVariable
	// 		@NotNull()
	// 		@Min(value = 5, message = "El ID debe ser mayor o igual a {value}") 
	// 		@Max(value = 20, message = "El ID debe ser menor o igual a {value}")
	// 		Integer id
	// ) {
	// 	ExampleDto estudiante = estudianteService.retornar(id);
	// 	return new ResponseEntity<EntityModel<?>>(assembler.toModel(estudiante), HttpStatus.OK);
	// }
	
	// @ApiOperation(value = "Crear estudiante", notes = "Se encarga de la inserción de un nuevo estudiante")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 201, message = "CREATED. El recurso se creó correctamente", response = ExampleDto.class),
    //     @ApiResponse(code = 400, message = "BAD REQUEST. La solicitud fue malformada, es posible que los datos no estén en el formato correcto", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 401, message = "UNAUTHORIZED. Usuario no autorizado para realizar esta petición", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 404, message = "NOT FOUND. El recurso no fue encontrado", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 409, message = "CONFLICT. La solicitud no se pudo completar debido a un conflicto con el recurso", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado en el sistema", response = ExceptionWrapper.class)
    // })
	// @PostMapping(value = "/insertar", consumes = "application/json")
	// public ResponseEntity<EntityModel<?>> guardar(@Valid @RequestBody ExampleDto estudiante) throws Exception {
	// 	estudiante = estudianteService.guardar(estudiante);
	// 	return new ResponseEntity<EntityModel<?>>(assembler.toModel(estudiante), HttpStatus.CREATED);
	// }
	
	// @ApiOperation(value = "Actualizar estudiante", notes = "Se encarga de la actualización de un estudiante")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 200, message = "OK. El recurso se actualizó correctamente", response = ExampleDto.class),
    //     @ApiResponse(code = 400, message = "BAD REQUEST. La solicitud fue malformada, es posible que los datos no estén en el formato correcto", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 401, message = "UNAUTHORIZED. Usuario no autorizado para realizar esta petición", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 404, message = "NOT FOUND. El recurso no fue encontrado", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 409, message = "CONFLICT. La solicitud no se pudo completar debido a un conflicto con el recurso", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado en el sistema", response = ExceptionWrapper.class)
    // })
	// @PutMapping(value = "/editar", consumes = "application/json")
	// public ResponseEntity<?> editar(@Valid @RequestBody ExampleDto estudiante) {
	// 	return new ResponseEntity<EntityModel<?>>(assembler.toModel(estudiante), HttpStatus.OK);
	// }
	
	// @ApiOperation(value = "Eliminar estudiante", notes = "Se encarga del borrado de un estudiante")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 204, message = "NO CONTENT. El recurso ha sido borrado correctamente"),
    //     @ApiResponse(code = 400, message = "BAD REQUEST. La solicitud fue malformada, es posible que los datos no estén en el formato correcto", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 401, message = "UNAUTHORIZED. Usuario no autorizado para realizar esta petición", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 404, message = "NOT FOUND. El recurso no fue encontrado", response = ExceptionWrapper.class ),
    //     @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado en el sistema", response = ExceptionWrapper.class)
    // })
	// @DeleteMapping(value = "/eliminar/{id}")
	// public ResponseEntity<?> eliminar(@PathVariable int id) {
	// 	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	// }
}
