package cundi.edu.co.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AutorEditorialDto {
	@NotNull(message = "El autor es requerido")
	private AutorDto autor;
	@NotNull(message = "La editorial es requerida")
	private EditorialDto editorial;
	@NotNull(message = "La fecha es requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	
	public AutorEditorialDto(){}
	
	public AutorEditorialDto(AutorDto autor, EditorialDto editorial, LocalDate fecha) {
		super();
		this.autor = autor;
		this.editorial = editorial;
		this.fecha = fecha;
	}

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}

	public EditorialDto getEditorial() {
		return editorial;
	}

	public void setEditorial(EditorialDto editorial) {
		this.editorial = editorial;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
