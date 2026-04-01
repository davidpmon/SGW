package com.example.demo.repositories;
import com.example.demo.models.UsuarioSupervisorId;
import com.example.demo.models.UsuarioSupervisor;
import com.example.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioSupervisorRepository extends JpaRepository<UsuarioSupervisor, UsuarioSupervisorId> {
    List<UsuarioSupervisor> findByUsuario_IdUsuario(Long idUsuario);//metodo de consulta del id del usuario
    List<UsuarioSupervisor> findBySupervisor_IdUsuario(Long idSupervisor);//metodo de consulta del id del supervisor

}
