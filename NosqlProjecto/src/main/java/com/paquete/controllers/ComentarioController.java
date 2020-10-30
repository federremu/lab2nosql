package com.paquete.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paquete.model.Comentario;
import com.paquete.repositorios.ComentariosRepository;
import com.paquete.service.SequenceGeneratorService;

@RestController
public class ComentarioController {
	
	@Autowired
	public ComentariosRepository comentariorepository;
	
	@GetMapping(value = "/allcomentarios")
	public List<Comentario> getAllComentarios() {
		return comentariorepository.findAll();
		
	}
	
	@PostMapping(value = "/addcomentario")
	public String crearComentario(@RequestBody Comentario comentario) {
		
		
		//user.setEmail("john.doe@example.com");
		//userRepository.save(user);
		
		
		Comentario nuevo = new Comentario();
		nuevo.setId(SequenceGeneratorService.generateSequence(Comentario.SEQUENCE_NAME));
		nuevo.setMeGustas(comentario.getMeGustas());
		nuevo.setNoMeGustas(comentario.getNoMeGustas());
		nuevo.setTexto(comentario.getTexto());
		nuevo.setUsuario(comentario.getUsuario());
		comentariorepository.save(nuevo);
		
		return "Se ha creado un nuevo comentario con el id "+ nuevo.getId();
		
	}
	
	

}
