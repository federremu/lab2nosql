package com.paquete.model;

import java.io.Serializable;
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
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String SEQUENCE_NAME = "comentarios_sequence";

	@Id
	private long id;

	private String texto;

	private String usuario;

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
		int contador=0;
		for (Emocion emocion : this.emociones) {
			if(emocion.isReaccion())
				contador++;
		}
		return contador;
	}

	public int getNoMeGustas() {
		int contador=0;
		for (Emocion emocion : this.emociones) {
			if(!emocion.isReaccion())
				contador++;
		}
		return contador;
	}

	public void agregarEmocion(Emocion emocion){
		this.emociones.add(emocion);
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", meGustas=" + getMeGustas() + ", noMeGustas=" + getNoMeGustas()
				+ "]";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ArrayList <Emocion> getEmociones(){
		return this.emociones;
	}

}