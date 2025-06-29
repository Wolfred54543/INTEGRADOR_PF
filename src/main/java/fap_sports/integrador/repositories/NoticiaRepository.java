package fap_sports.integrador.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fap_sports.integrador.models.Noticia;

// Repositorio para la entidad Noticia, extiende JpaRepository para
// proporcionar métodos CRUD y consultas básicas
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    List<Noticia> findByNotTipo(String notTipo);
}
