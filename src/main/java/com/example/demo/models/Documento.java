package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long idDocumento;
    @Column(nullable = false, length = 255)
    private String titulo;
    @Column(columnDefinition = "LONGTEXT")
    private String contenido;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipo;
    private String urlArchivo;
    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado= EstadoDocumento.BORRADOR; // Valor por defecto
    @Column(name = "fecha_creacion", nullable = false, updatable = false)//La fecha de creacion no debe cambiar nunca
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

//METODOS DE AUDITORÍA AUTOMATICA
    //Ganchos del ciclo de vida
    @PrePersist//anotacion JPA que indica que se debe ejecutar automaticamente apenas la entidad se guarde (persista) en la bd
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate//anotacion JPA que indica que se debe ejecutar automaticamente justo despues de que la entidad se actualice en la bd
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();// Se sobrescribe con la hora actual del cambio
    }
//esto tambien lo puede hacer mysql
}
