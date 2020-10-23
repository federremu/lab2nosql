package com.paquete.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paquete.model.Usuario;

@Repository
public interface UsuariosRepository extends MongoRepository<Usuario, String>{

}
