package com.example.demo.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//modelo para la tabla rol
@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = "roles")
public class Rol {

    @Id//Le dice a java y a spring que esto es un PK de SQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id_rol;

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 50)
    private String nombre_rol;

    //Un rol puede tener muchos permisos y un permiso puede estar en muchos roles
    @ManyToMany(fetch = FetchType.EAGER)//se usa eager para cargar todos los datos de la tabla
    @JoinTable
            (//se hace la relacion entre tablas de roles y permisos
            name = "roles_permisos",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    //usamos set para garantizar que un permiso no se repita dos veces en un mismo rol
    private Set<Permiso> permisos = new HashSet<>();//interfaz de coleccion de elementos
    //hacemos un set de permiso por que que es lo que este objeto tiene o posee".
    //en este caso un rol tiene un permiso o varios
    // hashset evita nullpointer inicializando la variable de inmediato
    @ManyToMany(mappedBy = "roles") // mappedBy, apuntando al atributo roles de Usuario
    private Set<Usuario> usuarios = new HashSet<>();
    /*mantiene la realcion bidireccional mostrando la realacion entre usuarios y roles
        evita crear otra tabla intermedia, podemos acceder a los usuarios que son admin
        Usuarios conoces roles y roles conoces a sus usuarios
    */

}
