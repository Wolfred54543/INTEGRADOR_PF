package fap_sports.integrador.controllers;

import fap_sports.integrador.models.Partido;
import fap_sports.integrador.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class InvitadoPartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping("/partidoInvitados")
    public String mostrarPartidosConCalendario(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            Model model) {

        List<Partido> todosPartidos = partidoService.listarPartidos();

        // Agrupamos por fecha (solo fechas con partidos)
        Map<LocalDate, List<Partido>> partidosPorFecha = todosPartidos.stream()
                .filter(p -> p.getParFecha() != null)
                .collect(Collectors.groupingBy(Partido::getParFecha, LinkedHashMap::new, Collectors.toList()));

        // Selección automática de fecha si no se pasó como parámetro
        if (fecha == null) {
            if (partidosPorFecha.containsKey(LocalDate.now())) {
                fecha = LocalDate.now();
            } else if (!partidosPorFecha.isEmpty()) {
                fecha = partidosPorFecha.keySet().stream().sorted().findFirst().orElse(null);
            }
        }

        List<Partido> partidosSeleccionados = partidosPorFecha.getOrDefault(fecha, List.of());

        // Preparar nombres de día abreviados en español
        Locale spanish = new Locale("es", "ES");
        DateTimeFormatter diaAbreviadoFormatter = DateTimeFormatter.ofPattern("E", spanish);
        
        Map<LocalDate, String> diasSemanaMap = new LinkedHashMap<>();
        for (LocalDate f : partidosPorFecha.keySet()) {
            diasSemanaMap.put(f, f.format(diaAbreviadoFormatter));
        }

        model.addAttribute("partidosPorFecha", partidosPorFecha);       // Map<LocalDate, List<Partido>>
        model.addAttribute("diasSemanaMap", diasSemanaMap);             // Map<LocalDate, String>
        model.addAttribute("fechaSeleccionada", fecha);                 // LocalDate seleccionada
        model.addAttribute("partidos", partidosSeleccionados);          // List<Partido> del día
        model.addAttribute("hoy", LocalDate.now());                     // Para resaltar fecha actual

        return "vistas/invitados/partidoInvitados"; // Solo una vista unificada
    }
}
