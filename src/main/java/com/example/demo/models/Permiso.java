package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//modelo para la tabla Permiso
@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos
@Entity
@Table(name = "permisos")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long idPermiso;
    @Column(name = "accion", unique = true, length = 50)
    private String accion;
    @Column(name ="descripcion", length = 100)
    private String descripcion;
}
