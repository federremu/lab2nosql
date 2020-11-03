package com.paquete.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document(collection = "emociones")
public class Emocion {

	@Transient
	public static final String SEQUENCE_NAME = "emociones_sequence";

	@Id
	private long id;

	private boolean reaccion;

	private String usuario;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    public boolean isReaccion() {
        return reaccion;
    }

    public void setReaccion(boolean reaccion) {
        this.reaccion = reaccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Emocion(){
        
    }

    public Emocion(boolean reaccion, String usuario) {
        this.reaccion = reaccion;
        this.usuario = usuario;
    }
    
}