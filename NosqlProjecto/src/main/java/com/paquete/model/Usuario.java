package com.paquete.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="usuarios")
public class Usuario {

	@Id
	private String email;

	@DBRef
    private ArrayList<Comentario> comentarios  = new ArrayList<>();

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

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	
	public void argegarComentario(Comentario comentario){
		this.comentarios.add(comentario);
	}
}
