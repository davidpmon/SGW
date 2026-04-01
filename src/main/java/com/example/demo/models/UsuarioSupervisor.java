package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//modelo para la tabla de relacion de un usuario con su supervisor
@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
//@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = ("usuarios_supervisores"))
public class UsuarioSupervisor {
    //llave compuesta
    @EmbeddedId//anotacion para definir que unos campos de esta clase se integran a otra tabla de otra entidad (UsuarioSupervisorId)
    private UsuarioSupervisorId id;

    @ManyToOne(fetch = FetchType.LAZY)//carga de datos perezosa, solo lo necesario
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSupervisor")
    @JoinColumn(name = "id_supervisor", nullable = false)
    private Usuario supervisor;

    public UsuarioSupervisor(Usuario usuario, Usuario supervisor) {
        this.usuario = usuario;
        this.supervisor = supervisor;
        this.id = new UsuarioSupervisorId(
                usuario.getIdUsuario(),
                supervisor.getIdUsuario()
        );

    }
}
