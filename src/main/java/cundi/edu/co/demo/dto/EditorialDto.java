package cundi.edu.co.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EditorialDto {
	private Integer id;
	@NotNull(message = "El nit es requerido")
	private String nit;
	@NotNull(message = "El nombre es requerido")
	private String nombre;
	@NotNull(message = "El correo es requerido")
	@Email(message = "Email incorrecto")
	private String correo;
	
	public EditorialDto() {}
	
	public EditorialDto(Integer id, String nit, String nombre, String correo) {
		super();
		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
		this.correo = correo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
