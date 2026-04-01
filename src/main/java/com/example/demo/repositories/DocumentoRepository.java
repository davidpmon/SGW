package com.example.demo.repositories;
import com.example.demo.models.EstadoDocumento;
import com.example.demo.models.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Documento;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    // El findById(Long id) ya existe por defecto, no hace falta escribirlo.

    // busca por coincidencia exacta de titulo
    Optional<Documento> findByTitulo(String titulo);
    // buscar por título que contenga una palabra (buscador tipo google)
    List<Documento> findByTituloContainingIgnoreCase(String palabraClave);

    // buscar todos los documentos de un mismo estado (ejemplo EN_REVISION)
    List<Documento> findByEstado(EstadoDocumento estado);

    // buscar por tipo y estado al mismo tiempo
    List<Documento> findByTipoAndEstado(TipoDocumento tipo, EstadoDocumento estado);

    // contar cuántos documentos hay de un tipo específico
    Long countByTipo(TipoDocumento tipo);
}
