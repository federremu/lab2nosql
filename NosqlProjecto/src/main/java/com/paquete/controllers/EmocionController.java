package com.paquete.controllers;

import java.util.List;
import java.util.Optional;

import com.paquete.model.Emocion;
import com.paquete.model.Usuario;
import com.paquete.repositorios.EmocionesRepository;
import com.paquete.repositorios.UsuariosRepository;
import com.paquete.service.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmocionController {
    @Autowired
	public EmocionesRepository emocionesRepository;
	@Autowired
    public UsuariosRepository usuariosRepository;
    
    @GetMapping(value = "/allemociones")
	public List<Emocion> getAllComentarios() {
		return emocionesRepository.findAll();
		
	}

  	@PostMapping(value = "/createEmocion")
	public String createEmocion(@RequestBody Emocion emocion) {
		System.out.println(emocion);
		Emocion nuevaEmocion = new Emocion();
		nuevaEmocion.setId(SequenceGeneratorService.generateSequence(Emocion.SEQUENCE_NAME));
		nuevaEmocion.setReaccion(emocion.isReaccion());
		nuevaEmocion.setUsuario(emocion.getUsuario());
		emocionesRepository.save(nuevaEmocion);
			
		/*
		System.out.println("creo edicion");
		Optional<Usuario> optionalEntity= usuariosRepository.findById(emocion.getUsuario().getEmail());
		Usuario usuario = optionalEntity.get();
		System.out.println("agarreUser");
		System.out.println(emocion);
		System.out.println(nuevo);
		usuario.argegarEmocion(nuevo);
		System.out.println("AgregeEmocion");
		usuariosRepository.save(usuario);
		System.out.println("Guardo");
		*/
		return "Se ha creado una nueva Emocion con id: "+ nuevaEmocion.getId();
	}
}
