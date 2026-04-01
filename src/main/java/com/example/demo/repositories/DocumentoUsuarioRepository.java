package com.example.demo.repositories;

import com.example.demo.models.DocumentoUsuario;
import com.example.demo.models.DocumentoUsuarioId;
import com.example.demo.models.RolDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoUsuarioRepository extends JpaRepository<DocumentoUsuario, DocumentoUsuarioId> {

    //buscar todos los documentos de un usuario en especifico
    List<DocumentoUsuario> findById_IdUsuario(Long idUsuario);

    /*Trae los documentos que participan un rol en especifico*/
    List<DocumentoUsuario> findById_IdDocumentoAndRolDocumento(Long idDocumento, RolDocumento rol);

    // Para cambiar el rol de un usuario en un documento rápidamente
    // Ejemplo: De EDITOR a SUPERVISOR
    Optional<DocumentoUsuario> findById_IdUsuarioAndId_IdDocumento(Long idUsuario, Long idDocumento);

    /*devuelve si un usuario esta asociado a un documento*/
    boolean existsById_IdUsuarioAndId_IdDocumento(Long idUsuario, Long idDocumento);

    /*Afirma o deniega la existencia de una relacion, por ejemplo si ya existe un documento con ciertos usuarios no deberia haber otro*/
    boolean existsById_IdUsuarioAndId_IdDocumentoAndRolDocumento(Long idUsuario, Long idDocumento, RolDocumento rol);
}
