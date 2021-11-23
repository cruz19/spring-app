package cundi.edu.co.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.service.IUsuarioService;

@SpringBootTest
class LineaIiiApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IAutorRepo autorRepo;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@BeforeEach
	void init() {
		System.out.println("BeforeEach...");
	}
	
	@Test
	void vaerificarClave() {
		System.out.println("Resultado:--------------------  " + bcrypt.encode("123456"));
		assertTrue(true);
	}
	
	@Test
	void validarSuma() {
		int resultado = usuarioService.sumar(1,2,3,7);
		assertEquals(resultado, 13);
	}
	
	@Test
	void validarExistenciaAutor() {
		int autorId = 1;
		Autor autor = autorRepo.findById(autorId).orElse(null);
		assertNotNull(autor);
	}

}