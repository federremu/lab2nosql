package com.paquete.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.paquete.model.Comentario;


@Repository
public interface ComentariosRepository extends MongoRepository<Comentario, Long>{
	List <Comentario> findByUsuario(String user);
}
