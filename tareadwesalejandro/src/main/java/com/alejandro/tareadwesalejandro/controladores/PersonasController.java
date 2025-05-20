package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.modelo.Personas;
import com.alejandro.tareadwesalejandro.servicios.ServiciosPersonas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personas")
public class PersonasController {

    @Autowired
    private ServiciosPersonas serviciosPersonas;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("personas", serviciosPersonas.listarTodas());
        return "personas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("persona", new Personas());
        return "personas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Personas persona) {
        serviciosPersonas.guardar(persona);
        return "redirect:/personas";
    }
    
	@GetMapping("/resgistrarPersona")
	public String registrarPersona() {
		return "registrarPersona"; // registrarPersona.html
	}
}
