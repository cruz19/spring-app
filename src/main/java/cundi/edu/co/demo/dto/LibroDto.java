package cundi.edu.co.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LibroDto {
	private Integer id;
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
	private String nombre;	
	
	@NotNull(message = "Descripcion es obligatorio")
	@Size(min = 3, max = 50, message = "la descripcion debe estar entre 3 y 50 caracteres")
	private String descripcion;	
	
	@NotNull(message = "Numero es obligatorio")
	private Integer numeroPaginas;
	
	@NotNull(message = "El autor es requerido")
	@JsonIgnoreProperties(value = { "libros" })
	private AutorDto autor;
	
	public LibroDto() {}

	public LibroDto(Integer id, String nombre, String descripcion, Integer numeroPaginas, AutorDto autor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}
	
}
