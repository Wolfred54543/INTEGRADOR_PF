package fap_sports.integrador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fap_sports.integrador.models.Comunicado;

// Repositorio para la entidad Comunicado, extiende JpaRepository para
// proporcionar métodos CRUD y consultas básicas
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {
    
}
