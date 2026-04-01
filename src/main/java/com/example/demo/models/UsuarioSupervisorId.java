//CLASE NECESARIA PARA USUARIOSUPERVISOR, REPRESENTA LA LLAVE COMPUESTA DE ESTA TABLA

package com.example.demo.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

//modelo para la tabla de relacion de un usuario con su supervisor
@Getter //genera los getters automaticamente con lombok
@Setter //genera los setters automaticamente con lombok
@NoArgsConstructor // Genera el constructor vacío (que pide JPA)
@AllArgsConstructor // Genera un constructor con todos los atributos

@Embeddable//JPA entiende que los atributos de esta clase se incrustan en UsuarioSupervisor
public class UsuarioSupervisorId implements Serializable {
    /*JPA necesita que las llaves compuestas sean serializables para manejar el cache de segundo nivel con mas largo plazo
*/
    private static final long serialVersionUID = 1L;//encargado de version de clase, si se cambia algo lanza exepcion, no fallo
    private Long idUsuario;
    private Long idSupervisor;


    /*Sirve para comparar dos objetos en memoria y evitar la creacion de duplicados*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioSupervisorId)) return false;
        UsuarioSupervisorId that = (UsuarioSupervisorId) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(idSupervisor, that.idSupervisor);
    }
    /*Sirve para generar un numero unico basado en los dos ID para organizar los objetos en sus mapas internos que corresponde
    cache del primer nivel haciendo mas eficiente el codigo*/
    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idSupervisor);
    }
}
