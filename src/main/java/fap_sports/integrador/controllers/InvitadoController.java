package fap_sports.integrador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvitadoController {
    @GetMapping("/invitado")
    public String admin() {
        return "vistas/invitado"; // Redirige a la vista de invitado cuando se accede a /invitado
    }
}
