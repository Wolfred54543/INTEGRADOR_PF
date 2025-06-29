package fap_sports.integrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fap_sports.integrador.models.Comunicado;
import fap_sports.integrador.repositories.ComunicadoRepository;

@Service
public class ComunicadoService {

    // Inyección automática del repositorio de Comunicados
    @Autowired
    private ComunicadoRepository comunicadoRepository;

    // Registra y guarda un nuevo comunicado en la base de datos
    public Comunicado registrarComunicado(Comunicado comunicado) {
        return comunicadoRepository.save(comunicado);
    }

    // Obtiene la lista completa de comunicados registrados
    public List<Comunicado> getAllComunicados() {
        return comunicadoRepository.findAll();
    }

    // Elimina un comunicado dado su ID
    public void deleteComunicado(Long id) {
        comunicadoRepository.deleteById(id);
    }

    // Obtiene un comunicado por su ID, lanza excepción si no existe
    public Comunicado getComunicadoById(Long id) {
        return comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado no encontrado con ID: " + id));
    }

    // Actualiza un comunicado existente en la base de datos
    public void updateComunicado(Comunicado comunicado) {
        comunicadoRepository.save(comunicado);
    }
}
