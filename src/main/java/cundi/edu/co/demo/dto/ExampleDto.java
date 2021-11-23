package cundi.edu.co.demo.dto;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;

public class ExampleDto {
	private Integer id;
	
	@ApiModelProperty(value = "Nombre", name= "nombre", dataType = "string", example = "Daniel", required = true)
	@NotNull(message = "El nombre es requerido")
	@Size(min = 5, max = 14, message = "El nombre debe tener una longitud entre {min} y {max} caracteres")
	private String nombre;

	@ApiModelProperty(value = "Apellido", name= "apellido", dataType = "string", example = "Barragan", required = true)
	@NotNull(message = "El apellido es requerido")
	private String apellido;

	@ApiModelProperty(value = "Email", name= "email", dataType = "string", example = "example@mail.com", required = true)
	@NotNull(message = "El email es requerido")
	@Email(message = "El formato del email no es válido")
	private String email;

	@ApiModelProperty(value = "Edad", name= "edad", dataType = "integer", example = "22", required = true)
	@NotNull(message = "La edad es requerida")
	@Min(value = 18, message = "La edad no debe ser menor a {value}")
	@Max(value = 150, message = "La edad no debe ser mayor a {value}")
	private Integer edad;
	
	@ApiModelProperty(value = "Fecha registro", name= "fecha", dataType = "date", example = "2020-01-01 00:00:00", required = true)
	@NotNull(message = "La fecha de registro es requerida")
	@Past(message = "La fecha debe ser anterior a la fecha actual")
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaRegistro;
	
	@ApiModelProperty(value = "Teléfono", name= "telefono", dataType = "string", example = "555-444-1010", required = true)
	@NotNull(message = "El número de teléfono es requerido")
	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "No es un número de teléfono válido")
	private String telefono;
	
	@ApiModelProperty(value = "Check", name= "check", dataType = "boolean", example = "true", required = true)
	@NotNull(message = "El check es requerido")
	@AssertTrue(message = "El check debe ser true")
	private Boolean check;
	
	public ExampleDto() {}
	
	public ExampleDto(Integer id, String nombre, String apellido, String email, Integer edad, Date fechaRegistro, String telefono, Boolean check) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.edad = edad;
		this.fechaRegistro = fechaRegistro;
		this.telefono = telefono;
		this.check = check;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}
	
}
