package fap_sports.integrador.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fap_sports.integrador.models.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
}