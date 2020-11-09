package com.paquete.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paquete.model.Usuario;
import com.paquete.repositorios.UsuariosRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	public UsuariosRepository usuariosrespository;
	
	@Cacheable(value = "users")
	@GetMapping(value = "/all")
	public List<Usuario> getAllUsuarios() {
		return usuariosrespository.findAll();
		
	}
	
	@PostMapping(value = "/create")
	public String createUsuario(@RequestBody Usuario usuario) {
		String msj;
		if (!usuariosrespository.findById(usuario.getEmail()).isPresent()){
			Usuario nuevo = usuariosrespository.insert(usuario);
			msj ="Se ha creado un nuevo usuario con el mail "+ nuevo.getEmail();
		}else {
			msj = "El usuario ya existe, ingrese un mail diferente ";
		}
		
		return msj;
		
	}
	
	

}
