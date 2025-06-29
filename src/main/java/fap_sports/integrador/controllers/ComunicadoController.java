package fap_sports.integrador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fap_sports.integrador.models.Comunicado;
import fap_sports.integrador.services.ComunicadoService;

@Controller
public class ComunicadoController {

    @Autowired
    private ComunicadoService comunicadoService; // Inyecta el servicio que maneja la lógica de negocio relacionada a comunicados

    @GetMapping("/formularioComunicados")
    public String formularioComunicados(Model model) {
        model.addAttribute("comunicado", new Comunicado()); // Objeto vacío para el formulario
        model.addAttribute("comunicados", comunicadoService.getAllComunicados()); // Lista de comunicados existentes
        return "vistas/comunicados"; // Devuelve la vista del formulario
    }

    @PostMapping("/registrarComunicados")
    public String registrarComunicados(@ModelAttribute Comunicado comunicado) {
        comunicadoService.registrarComunicado(comunicado); // Guarda el nuevo comunicado
        return "redirect:/formularioComunicados"; // Redirige para evitar reenvío del formulario y actualizar la lista
    }
}
