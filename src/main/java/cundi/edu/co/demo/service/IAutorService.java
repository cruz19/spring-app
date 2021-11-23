package cundi.edu.co.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IAutorService extends ICrud<AutorDto, Integer> {
	public AutorDto retonarPorIdDetalle(Integer id) throws ModelNotFoundException;
	public AutorDto buscarPorNombreLibro(String nombre) throws ModelNotFoundException;
	/////////////////////////////////////////////////////////////////////////////////
	public Page<AutorDto> contieneNombre(String nombre, Pageable page);
	public AutorDto buscarPorCorreo(String correo) throws ModelNotFoundException;
	public AutorDto buscarPorCedula(String cedula) throws ModelNotFoundException;
	public AutorDto buscarPorCedulaYCorreo(String cedula, String correo) throws ModelNotFoundException;
	public Page<AutorDto> correoTerminaEn(String correo, Pageable pageable);
	public Page<AutorDto> contieneCorreo(String correo, Pageable pageable);
	public Page<AutorDto> obtenerPaginadoOrdenarPorNombreDesc(Pageable pageable);
}
