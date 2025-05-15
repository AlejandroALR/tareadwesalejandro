package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.modelo.Credenciales;
import com.alejandro.tareadwesalejandro.servicios.ServiciosCredenciales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credenciales")
public class CredencialesController {

    @Autowired
    private ServiciosCredenciales serviciosCredenciales;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("credenciales", serviciosCredenciales.findAll());
        return "credenciales/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("credencial", new Credenciales());
        return "credenciales/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Credenciales credencial) {
        serviciosCredenciales.insertarCredenciales(credencial);
        return "redirect:/credenciales";
    }
}
