package cundi.edu.co.demo.assembler;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

// import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.server.RepresentationModelAssembler;
// import org.springframework.stereotype.Component;

// import cundi.edu.co.demo.controller.EstudianteController;
// import cundi.edu.co.demo.dto.ExampleDto;

// @Component
// public class EstudianteModelAssembler implements RepresentationModelAssembler<ExampleDto, EntityModel<ExampleDto>> {

// 	@Override
// 	public EntityModel<ExampleDto> toModel(ExampleDto entity) {
// 	    return EntityModel.of(entity,
// 	            linkTo(methodOn(EstudianteController.class).retornar(entity.getId())).withSelfRel(),
// 	            linkTo(methodOn(EstudianteController.class).editar(entity)).withRel("editar"),
// 	            linkTo(methodOn(EstudianteController.class).eliminar(entity.getId())).withRel("eliminar"),
// 	    		linkTo(methodOn(EstudianteController.class).listar()).withRel("estudiantes"));
// 	}

// }
