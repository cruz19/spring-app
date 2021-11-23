package cundi.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.service.IAutorEditorialService;

@RestController
@RequestMapping("/autores-editoriales")
public class AutorEditorialController {
	
	@Autowired
	private IAutorEditorialService service;
	
	@GetMapping(value = "/obtenerPaginado", produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<AutorEditorialDto> results  = service.retornarPaginado(page);
		return new ResponseEntity<Page<AutorEditorialDto>>(results, HttpStatus.OK);
	}

}
