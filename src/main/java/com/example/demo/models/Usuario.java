package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id//Le dice a java y a spring que esto es un PK de SQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;
    @Column(length = 100, unique = true)
    private String usuario;
    @Column(nullable = false, length = 255)
    private String contraseña;
    @Column(nullable = false, unique = true, length = 255)
    private String correo;
    private byte[] estado;

    // Relación muchos usuarios pueden tener varios roles al tiempo
    @ManyToMany(fetch = FetchType.EAGER)// se usa eager para cargar todos los datos de la tabla
    @JoinTable(//para ver los roles de un usuario tendra que ir a ver usuario_roles que es la intermediaria
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),//en la tabla de usuario_roles la parte de la clase usuario es id_usuario
            inverseJoinColumns = @JoinColumn(name = "id_rol")//para ver el rol de un usuario se teiene que ver el id_rol de la clase usuario_roles
    )
    //usamos set para garantizar que un rol no se repita dos veces en un mismo usuario
    private Set<Rol> roles;//contenedor que guardara los objetos de rol
    //hacemos un set de rol por que que es lo que este objeto tiene o posee?".
    //en este caso usuario tiene un rol

}

