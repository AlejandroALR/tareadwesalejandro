package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.modelo.Ejemplares;
import com.alejandro.tareadwesalejandro.servicios.ServiciosEjemplares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ejemplares")
public class EjemplaresController {

    @Autowired
    private ServiciosEjemplares serviciosEjemplares;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ejemplares", serviciosEjemplares.listarTodos());
        return "ejemplares/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("ejemplar", new Ejemplares());
        return "ejemplares/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ejemplares ejemplar) {
        serviciosEjemplares.guardar(ejemplar);
        return "redirect:/ejemplares";
    }
    
	@GetMapping("/gestionEjemplares")
	public String gestionEjemplares() {
		return "/ejemplares/gestionEjemplares"; // gestionEjemplares.html
	}
}
