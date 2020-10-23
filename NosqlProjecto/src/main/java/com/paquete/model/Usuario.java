package com.paquete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="usuarios")
public class Usuario {

	@Id
	private String email;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	
}
