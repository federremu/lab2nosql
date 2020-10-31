package com.paquete.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document(collection = "comentarios")
public class Comentario {

	@Transient
	public static final String SEQUENCE_NAME = "comentarios_sequence";

	@Id
	private long id;

	private String texto;

	private int meGustas = 0;

	private int noMeGustas = 0;	

	@DBRef
	private Usuario usuario;

	@DBRef
	private ArrayList<Emocion> emociones  = new ArrayList<>();

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

	public void agregarEmocion(Emocion emocion){
		this.emociones.add(emocion);
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", meGustas=" + meGustas + ", noMeGustas=" + noMeGustas
				+ "]";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}