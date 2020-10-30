package com.paquete.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="usuarios")
public class Usuario {

	@Id
	private String email;

	@DBRef
    private Collection<Comentario> comentarios;

	@DBRef
	private Collection<Emocion> emociones;
	
	public Usuario() {
		super();
	}
	
	//no usa id porque lo generara mongo 
	public Usuario(String email) {
		super();
		this.email = email;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Collection<Emocion> getEmociones() {
		return emociones;
	}

	public void setEmociones(Collection<Emocion> emociones) {
		this.emociones = emociones;
	}
	
	public void argegarEmocion(Emocion emocion){
		this.emociones.add(emocion);
	}

	public void argegarComentario(Comentario comentario){
		this.comentarios.add(comentario);
	}
}
