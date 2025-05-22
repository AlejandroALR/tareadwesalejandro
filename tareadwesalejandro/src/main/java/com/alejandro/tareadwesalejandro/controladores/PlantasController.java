package com.alejandro.tareadwesalejandro.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alejandro.tareadwesalejandro.modelo.Plantas;
import com.alejandro.tareadwesalejandro.servicios.ServiciosPlantas;

@Controller
@RequestMapping("/plantas")
public class PlantasController {

    @Autowired
    private ServiciosPlantas serviciosplantas;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("plantas", serviciosplantas.listarTodas());
        return "plantas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("planta", new Plantas());
        return "plantas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Plantas planta) {
    	serviciosplantas.guardarOActualizar(planta);
        return "redirect:/plantas";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo) {
    	serviciosplantas.eliminarPorCodigo(codigo);
        return "redirect:/plantas";
    }

	@GetMapping("/gestionPlantas")
	public String gestionPlantas() {
		return "/plantas/gestionPlantas"; // gestionPlantas.html
	}
	
	@GetMapping("/verPlantas")
	public String verPlantas(Model model) {
	    model.addAttribute("plantas", serviciosplantas.listarTodas());
	    return "/plantas/verPlantas"; // verPlantas.html
	}
	
    @GetMapping("/registrarPlanta")
    public String registrarPlanta(Model model) {
        model.addAttribute("planta", new Plantas());
        model.addAttribute("plantas", serviciosplantas.listarTodas());
        return "/plantas/registrarPlanta";
    }
    
    @PostMapping("/registrarPlanta")
    public String procesarRegistroPlanta(@ModelAttribute Plantas planta, RedirectAttributes redirectAttributes) {
        planta.setCodigo(planta.getCodigo().toUpperCase());
        
        if (serviciosplantas.existePorCodigo(planta.getCodigo())) {
            redirectAttributes.addFlashAttribute("error", "Ya existe una planta con el código " + planta.getCodigo());
            return "redirect:/plantas/registrarPlanta";
        }
        
        serviciosplantas.guardarOActualizar(planta);
        
        redirectAttributes.addFlashAttribute("mensaje", "Planta registrada con éxito");
        
        return "redirect:/plantas/registrarPlanta";
    }
    
    @GetMapping("/limpiarFormulario")
    public String limpiarFormulario(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("planta", new Plantas());
        return "redirect:/plantas/registrarPlanta";
    }
}

