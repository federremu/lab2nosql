## Twitter (lab2nosql)
Segundo Laboratorio del curso no sql  

# Dependencias:
  - mongodb (4.4.1 utilizado)
  - redis (5.0.7 utilizado)
  - postman (opcional)

# Lenguaje usado
  -java 1.8
  
 # Tipo de servicio
    Rest
  
 # Servidor 
  tomcat (localmente, levantado por Spring Boot)
 
# Instalación 
  >Iniciar los sevicios de Mongodb y Redis
  >Se puede correr la app con el script run.sh (verificar los puertos de los servicios previamente)

# Arquitectura general

![myimage-alt-tag](https://github.com/federremu/lab2nosql/blob/main/NosqlProjecto/documentacion/2020-11-12.png) 

  # Diagrama de despliegue
![myimage-alt-tag](https://github.com/federremu/lab2nosql/blob/main/NosqlProjecto/documentacion/2020-11-12%20(1).png) 

# Diagrama de clases

![myimage-alt-tag](https://github.com/federremu/lab2nosql/blob/main/NosqlProjecto/documentacion/2020-11-12%20(2).png)

# Puertos
    redis.port=6379
    server.port=6040
    mongodb.uri=mongodb://localhost:27017/lab2DB
    
  
