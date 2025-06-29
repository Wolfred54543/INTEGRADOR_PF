package fap_sports.integrador.repositories;

import fap_sports.integrador.models.Reclamo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {
    List<Reclamo> findByRecEstado(String recEstado); // Buscar por estado: "abierto", "cerrado"
    List<Reclamo> findByRecMotivoContainingIgnoreCase(String keyword); // Buscar por palabra clave en motivo
    List<Reclamo> findByRecEstadoIgnoreCase(String recEstado);

}
