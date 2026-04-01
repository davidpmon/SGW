package com.example.demo.repositories;

import com.example.demo.models.Usuario;
import com.example.demo.models.UsuarioSupervisor;
import com.example.demo.models.UsuarioSupervisorId;
import org.junit.jupiter.api.Test; // mportante vJUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Esto hace que sea un test real
class UsuarioSupervisorRepositoryTest {

    @Autowired
    private UsuarioSupervisorRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo; // necesitamos este para guardar los usuarios primero

    @Test
    void probarRelacionSupervisor() {
        // creamos los usuarios en la BD de prueba
        Usuario laura = new Usuario();
        laura.setNombre("Laura");
        laura.setApellido("Gomez");
        laura.setCedula("1");
        laura.setUsuario("lgomez1");
        laura.setContraseña("hola");
        laura.setCorreo("lgomez1@");
        usuarioRepo.save(laura);//aca ya se les asigno el ID desde la bd, no es necesario hacerlo manual

        Usuario carlos = new Usuario();
        carlos.setNombre("Carlos");
        carlos.setApellido("Ramirez");
        carlos.setCedula("2");
        carlos.setUsuario("cramirez2");
        carlos.setContraseña("chao");
        carlos.setCorreo("cramirez2@");
        usuarioRepo.save(carlos);//aca ya se les asigno el ID desde la bd, no es necesario hacerlo manual

        // Creacion la entidad de la relación
        UsuarioSupervisor relacion = new UsuarioSupervisor();
        relacion.setId(new UsuarioSupervisorId(laura.getIdUsuario(), carlos.getIdUsuario()));
        relacion.setUsuario(laura);
        relacion.setSupervisor(carlos);

        repo.save(relacion);

        // verificacion usamos Assertions para definir el exito de la preuba
        List<UsuarioSupervisor> lista = repo.findByUsuario_IdUsuario(laura.getIdUsuario());

        assertFalse(lista.isEmpty(), "La lista no debería estar vacía");
        assertEquals("Carlos", lista.get(0).getSupervisor().getNombre());
    }
}