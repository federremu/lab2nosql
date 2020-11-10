package com.paquete.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	@Cacheable(value = "comentarios")
	@GetMapping(value = "/allcomentarios")
	public List<Comentario> getAllComentarios() {
		return comentariorepository.findAll();
		
	}
	@GetMapping(value = "/allcomentariosUser")
	public List<Comentario> getAllComentariosoUser(@RequestParam("user") String user) {
		return comentariorepository.findByUsuario(user);
	}

	@GetMapping(value = "/leerComentario")
	public Optional<Comentario> getAllComentariosoUser(@RequestParam("comentario") Long idComentario) {
		return comentariorepository.findById(idComentario);
	}



	@CacheEvict(value = "comentarios", allEntries = true)
	@PostMapping(value = "/addcomentario")
	public String crearComentario(@RequestBody Comentario comentario) {
		
		String msj;
		if (existeUsuario(comentario.getUsuario())){
			Comentario nuevo = new Comentario();
			nuevo.setId(SequenceGeneratorService.generateSequence(Comentario.SEQUENCE_NAME));
			nuevo.setTexto(comentario.getTexto());
			nuevo.setUsuario(comentario.getUsuario());
			Comentario c=comentariorepository.save(nuevo);

			Optional<Usuario> optionalEntityU= usuariosRepository.findById(comentario.getUsuario());
			Usuario usuario = optionalEntityU.get();
			usuario.argegarComentario(c);
			usuariosRepository.save(usuario);
			msj ="Se ha creado un nuevo comentario con el id "+ nuevo.getId();
		}else {
			msj="el usuario ingresado no existe";
		}


		return msj;
		
	}
	
	@PostMapping(value = "/addReaccion")
	public String agregarReaccion(@RequestParam("user") String user, @RequestParam("comentario") long comentario, @RequestParam("emocion")  Boolean emocion) {
		
		String msj;

		Optional<Comentario> optionalEntityC= comentariorepository.findById(comentario);
		Comentario c = optionalEntityC.get();
		if ((existeUsuario(user)) && (!usuarioYaReacciono(user, c))){
			Emocion nuevaEmocion = new Emocion();
			nuevaEmocion.setId(SequenceGeneratorService.generateSequence(Emocion.SEQUENCE_NAME));
			nuevaEmocion.setReaccion(emocion);
			nuevaEmocion.setUsuario(user);

			Emocion e=emocionRepository.save(nuevaEmocion);
			c.agregarEmocion(e);

			comentariorepository.save(c);

			msj= "Se ha reaccionado al comentario con emocion con el id "+ e.getId();
		
		}else{
			msj= "El usaurio no existe o ya reacciono a este comentario";
		}
		return msj;
	}
	
	
	//retorna true si el usuario ya ha reaccionado a ese comentario
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
	
	//retorna true si el usuario existe
	boolean existeUsuario (String mail) {
		return usuariosRepository.findById(mail).isPresent();
	}
	

}
