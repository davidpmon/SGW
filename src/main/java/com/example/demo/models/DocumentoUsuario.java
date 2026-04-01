package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = "documentos_usuario")
public class DocumentoUsuario {
    /*@ManyToOne define la cardinalidad muchos registros de DocumentoUsuario pueden pertenecer a un registro de Usuario*/
    @EmbeddedId
    private DocumentoUsuarioId id;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_documento", nullable = false)
    private RolDocumento rolDocumento;
    @ManyToOne(fetch = FetchType.LAZY)//ahorrador de carga, carga perezosa
    @MapsId("idDocumento")//inidicador a JPA que no es una columna nueva, es una columna que se integro de la clase DocumentoUsuarioID
    @JoinColumn(name = "id_documento", insertable = false, updatable = false)//direccion exacta del vinclo
    private Documento documento;
    @ManyToOne(fetch = FetchType.LAZY)//ahorrador de carga de datos
    @MapsId("idUsuario")//inidicador a JPA que no es una columna nueva, es una columna que se integro de la clase DocumentoUsuarioID
    //utilizado para insertar el id dentro de la llave compuesta
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)// direccion exacta del vinculo
    private Usuario usuario;
}
