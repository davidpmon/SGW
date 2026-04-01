package com.example.demo.repositories;

import com.example.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
//se le dice a spring que Usuario este es el repositorio para manejar la tabla de usuarios
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //query method en este caso consulta por nombre
    //optional es un contenedor seguro, si no existe el usuario el programa no explota
    Optional<Usuario> findByUsuario(String usuario); // solo esto es suficiente
    //spring entiende atraves de findBy que debe hacer una consulta de tipo select con where, una maravilla
    //@Query("SELECT u FROM Usuario u JOIN FETCH u.roles r JOIN FETCH r.permisos WHERE u.usuario = :usuario")
    //Optional<Usuario> findByUsuario(@Param("usuario") String usuario);
}