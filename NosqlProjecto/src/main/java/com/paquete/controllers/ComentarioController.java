package com.paquete.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paquete.model.Comentario;
import com.paquete.model.Emocion;
import com.paquete.model.Usuario;
import com.paquete.repositorios.ComentariosRepository;
import com.paquete.repositorios.EmocionesRepository;
import com.paquete.repositorios.UsuariosRepository;
import com.paquete.service.SequenceGeneratorService;

@RestController
public class ComentarioController {
	
	@Autowired
	public ComentariosRepository comentariorepository;

	@Autowired
	public EmocionesRepository emocionRepository;

	@Autowired
    public UsuariosRepository usuariosRepository;
	
	@GetMapping(value = "/allcomentarios")
	public List<Comentario> getAllComentarios() {
		return comentariorepository.findAll();
		
	}
	@GetMapping(value = "/allcomentariosUser")
	public List<Comentario> getAllComentariosoUser(@RequestParam("user") String user) {
		return comentariorepository.findByUsuario(user);
	}


	
	@PostMapping(value = "/addcomentario")
	public String crearComentario(@RequestBody Comentario comentario) {
		
		
		//user.setEmail("john.doe@example.com");
		//userRepository.save(user);
		
		
		Comentario nuevo = new Comentario();
		nuevo.setId(SequenceGeneratorService.generateSequence(Comentario.SEQUENCE_NAME));
		nuevo.setTexto(comentario.getTexto());
		nuevo.setUsuario(comentario.getUsuario());
		Comentario c=comentariorepository.save(nuevo);

		Optional<Usuario> optionalEntityU= usuariosRepository.findById(comentario.getUsuario());
		Usuario usuario = optionalEntityU.get();
		usuario.argegarComentario(c);
		usuariosRepository.save(usuario);

		return "Se ha creado un nuevo comentario con el id "+ nuevo.getId();
		
	}
	
	@PostMapping(value = "/addReaccion")
	public String agregarReaccion(@RequestParam("user") String user, @RequestParam("comentario") long comentario, @RequestParam("emocion")  Boolean emocion) {
		

		System.out.println(user);
		System.out.println(comentario);
		System.out.println(emocion);
		//revisar si user existe y si ya reacciono antes	
		//Optional<Usuario> optionalEntityU= usuariosRepository.findById(user);
		//Usuario usuario = optionalEntityU.get();
		//System.out.println(usuario.getEmail());
		Optional<Comentario> optionalEntityC= comentariorepository.findById(comentario);
		Comentario c = optionalEntityC.get();
		boolean yaReacciono = usuarioYaReacciono(user, c);
		if (!yaReacciono){
			Emocion nuevaEmocion = new Emocion();
			nuevaEmocion.setId(SequenceGeneratorService.generateSequence(Emocion.SEQUENCE_NAME));
			nuevaEmocion.setReaccion(emocion);
			nuevaEmocion.setUsuario(user);

			Emocion e=emocionRepository.save(nuevaEmocion);
			c.agregarEmocion(e);

			comentariorepository.save(c);
			System.out.println(c.getEmociones());
			System.out.println("Guardo");

			return "Se ha reaccionado al comentario con emocion con el id "+ e.getId();
		}else{
			return "El usaurio ya reacciono al comentario: "+comentario;
		}
	}

	boolean usuarioYaReacciono(String user, Comentario comentario){
		boolean reacciono = false;
		ArrayList <Emocion> emociones=comentario.getEmociones();
		for (Emocion emocion : emociones) {
			if (emocion.getUsuario().equals(user)){
				reacciono = true;
				return reacciono;
			}
		}
		return reacciono;
	}
	

}
