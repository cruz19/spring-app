package cundi.edu.co.demo.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class AutorDto {
	private Integer id;
	
	@NotNull(message = "Cedula es obligatorio")
	@Size(min = 7, max = 12, message = "La cédula debe estar entre 7 y 12 caracteres")
	private String cedula;	
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres")
	private String nombre;
	
	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres")	
	private String apellido;
	
	@NotNull(message = "Correo es obligatorio")
	@Email(message = "Email incorrecto")
	private String correo;

	@Valid
	@JsonIgnoreProperties(value = { "autor" })
	private List<LibroDto> libros;
	
	public AutorDto() {}

	public AutorDto(Integer id, String cedula, String nombre, String apellido, String correo, List<LibroDto> libros) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.libros = libros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<LibroDto> getLibros() {
		return libros;
	}

	public void setLibros(List<LibroDto> libros) {
		this.libros = libros;
	}
	
}
