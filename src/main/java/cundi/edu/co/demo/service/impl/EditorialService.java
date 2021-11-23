package cundi.edu.co.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.EditorialDto;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IEditorialRepo;
import cundi.edu.co.demo.service.IEditorialService;

@Service
public class EditorialService implements IEditorialService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IEditorialRepo editorialRepo;

	@Override
	public Page<EditorialDto> retornarPaginado(int page, int size) { return null; }

	@Override
	public Page<EditorialDto> retornarPaginado(Pageable page) {
		Page<EditorialDto> editoriales = editorialRepo.findAll(page).map(a -> modelMapper.map(a, EditorialDto.class));
		return editoriales;
	}

	@Override
	public EditorialDto retonarPorId(Integer id) throws ModelNotFoundException {
		Editorial editorial = editorialRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("Editorial no encontrado"));
		EditorialDto dto = modelMapper.map(editorial, EditorialDto.class);
		return dto;
	}

	@Override
	public void guardar(EditorialDto dto) throws ConflictException {
		Editorial editorial = modelMapper.map(dto, Editorial.class);
		editorialRepo.save(editorial);
	}

	@Override
	public void editar(EditorialDto dto) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		if (dto.getId() == null)
			throw new ArgumentRequiredException("ID editorial es requerido");
		
		if (!editorialRepo.existsById(dto.getId()))
			throw new ModelNotFoundException("Editorial no encontrado");
		Editorial editorial = modelMapper.map(dto, Editorial.class);

		editorialRepo.save(editorial);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		if (!editorialRepo.existsById(id))
			throw new ModelNotFoundException("Editorial no encontrado");

		editorialRepo.deleteById(id);
	}

}
