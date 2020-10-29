package com.paquete.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paquete.model.Comentario;


@Repository
public interface ComentariosRepository extends MongoRepository<Comentario, Long>{
	

}
