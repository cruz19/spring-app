package cundi.edu.co.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

	@GetMapping(value = "/saludo", produces = "application/json")
	public ResponseEntity<String> saludar() {
		return new ResponseEntity<String>("HOLA MUNDO 10:28 p.m", HttpStatus.OK);
	}

}
