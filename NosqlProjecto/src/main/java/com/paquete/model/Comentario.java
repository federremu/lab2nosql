package com.paquete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document(collection = "comentarios")
public class Comentario {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;

	private String texto;

	private int meGustas;

	private int noMeGustas;
	
	//private Usuario usuario;
	

	public Comentario() { }

	public Comentario(String text) {
		this.texto = text;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getMeGustas() {
		return meGustas;
	}

	public void setMeGustas(int meGustas) {
		this.meGustas = meGustas;
	}

	public int getNoMeGustas() {
		return noMeGustas;
	}

	public void setNoMeGustas(int noMeGustas) {
		this.noMeGustas = noMeGustas;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", meGustas=" + meGustas + ", noMeGustas=" + noMeGustas
				+ "]";
	}

	

}