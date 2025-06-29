package fap_sports.integrador.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fap_sports.integrador.models.Noticia;
import fap_sports.integrador.services.NoticiaService;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService; // Inyecta el servicio que maneja la lógica de negocio relacionada a comunicados

    @GetMapping("/formularioNoticias")
    public String formularioNoticias(Model model) {
        model.addAttribute("noticia", new Noticia()); // Objeto vacío para el formulario
        model.addAttribute("noticias", noticiaService.getAllNoticias()); // Lista de comunicados existentes
        return "vistas/noticiaFormulario"; // Devuelve la vista del formulario
    }

    @PostMapping("/registrarNoticias")
    public String registrarNoticias(@ModelAttribute Noticia noticia) {
        noticiaService.registrarNoticia(noticia); // Guarda el nuevo comunicado
        return "redirect:/formularioNoticias"; // Redirige para evitar reenvío del formulario y actualizar la lista
    }

    @GetMapping("/editarNoticia/{id}")
    public String editarNoticia(@PathVariable("id") Long id, Model model) {
        Noticia noticia = noticiaService.getNoticiaById(id);
        if (noticia == null) {
            return "redirect:/formularioNoticias";
        }
        model.addAttribute("noticia", noticia);
        model.addAttribute("noticias", noticiaService.getAllNoticias());
        return "vistas/editarNoticia";
    }

    @PostMapping("/actualizarNoticia")
    public String actualizarNoticia(@ModelAttribute Noticia noticia) {
        Noticia noticiaExistente = noticiaService.getNoticiaById(noticia.getNotId());
        if (noticiaExistente != null) {
            noticia.setNotFechaCreacion(noticiaExistente.getNotFechaCreacion());
        }
        noticiaService.registrarNoticia(noticia);
        return "redirect:/formularioNoticias";
    }


    @PostMapping("/eliminarNoticia/{id}")
    public String eliminarNoticia(@PathVariable("id") Long id) {
        noticiaService.deleteNoticia(id);
        return "redirect:/formularioNoticias";
    }


     // Muestra la página de noticias para invitados, con noticias principales y secundarias
    @GetMapping("/noticias")
    public String noticias(Model model) {
        List<Noticia> noticiasPrincipales = noticiaService.obtenerNoticiasPorTipo("principal");
        List<Noticia> noticiasSecundarias = noticiaService.obtenerNoticiasPorTipo("secundaria");

        model.addAttribute("noticiasPrincipales", noticiasPrincipales);
        model.addAttribute("noticiasSecundarias", noticiasSecundarias);
        return "vistas/invitados/noticias";
    }
    @GetMapping("/postNoticia/{id}")
    public String verNoticia(@PathVariable("id") Long id, Model model) {
        Noticia noticia = noticiaService.getNoticiaById(id);
        if (noticia == null) {
            return "redirect:/noticias"; // o una página de error
        }
        model.addAttribute("noticia", noticia);
        return "vistas/invitados/postNoticia"; // aquí va tu HTML de detalle
    }

}
