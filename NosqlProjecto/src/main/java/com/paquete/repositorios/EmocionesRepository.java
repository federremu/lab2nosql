package com.paquete.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paquete.model.Emocion;


@Repository
public interface EmocionesRepository extends MongoRepository<Emocion, Long>{

}
