package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.modelo.Mensajes;
import com.alejandro.tareadwesalejandro.servicios.ServiciosMensajes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mensajes")
public class MensajesController {

    @Autowired
    private ServiciosMensajes serviciosMensajes;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mensajes", serviciosMensajes.listarTodos());
        return "mensajes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("mensaje", new Mensajes());
        return "mensajes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mensajes mensaje) {
        serviciosMensajes.guardar(mensaje);
        return "redirect:/mensajes";
    }
    
	@GetMapping("/gestionMensajes")
	public String gestionMensajes() {
		return "/mensajes/gestionMensajes"; // gestionMensajes.html
	}
}
