package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.dto.FiltroEjemplarDTO;
import com.alejandro.tareadwesalejandro.dto.RegistroEjemplarDTO;
import com.alejandro.tareadwesalejandro.modelo.Ejemplares;
import com.alejandro.tareadwesalejandro.modelo.Mensajes;
import com.alejandro.tareadwesalejandro.repositorios.EjemplaresRepository;
import com.alejandro.tareadwesalejandro.servicios.ServiciosEjemplares;
import com.alejandro.tareadwesalejandro.servicios.ServiciosPersonas;
import com.alejandro.tareadwesalejandro.servicios.ServiciosPlantas;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ejemplares")
public class EjemplaresController {

    @Autowired
    private ServiciosEjemplares serviciosEjemplares;
    
    @Autowired
    private ServiciosPlantas serviciosPlantas;
    
    @Autowired
    private ServiciosPersonas serviciosPersonas;
    
    @Autowired
    private EjemplaresRepository ejemplaresRepository;

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
	
	@GetMapping("/registrarEjemplar")
	public String mostrarFormularioRegistroEjemplar(Model model) {
	    model.addAttribute("registro", new RegistroEjemplarDTO());
	    model.addAttribute("plantas", serviciosPlantas.listarTodas());
	    return "ejemplares/registrarEjemplar";
	}

	@PostMapping("/registrarEjemplar")
	public String procesarRegistroEjemplar(@Valid @ModelAttribute("registro") RegistroEjemplarDTO dto,
	                                       BindingResult result,
	                                       Model model,
	                                       RedirectAttributes redirectAttributes,
	                                       Authentication auth) {
	    if (result.hasErrors()) {
	        model.addAttribute("plantas", serviciosPlantas.listarTodas());
	        return "ejemplares/registrarEjemplar";
	    }

	    Long idPersona = serviciosPersonas.obtenerIdDesdeAuth(auth);
	    serviciosEjemplares.registrarEjemplarConMensajeInicial(dto, idPersona);

	    redirectAttributes.addFlashAttribute("mensaje", "Ejemplar registrado correctamente.");
	    return "redirect:/ejemplares/registrarEjemplar";
	}
	
	@GetMapping("/filtrarEjemplares")
	public String mostrarFormularioFiltrado(Model model) {
	    model.addAttribute("filtro", new FiltroEjemplarDTO());
	    model.addAttribute("plantas", serviciosPlantas.listarTodas());
	    model.addAttribute("resultados", Collections.emptyList());
	    return "ejemplares/filtrarEjemplares";
	}

	@PostMapping("/filtrarEjemplares")
	public String procesarFiltroEjemplares(@ModelAttribute("filtro") FiltroEjemplarDTO filtro,
	                                       Model model) {
	    List<Ejemplares> resultados = serviciosEjemplares.buscarEjemplaresPorPlantas(filtro.getCodigosPlantas());

	    model.addAttribute("filtro", filtro);
	    model.addAttribute("plantas", serviciosPlantas.listarTodas());
	    model.addAttribute("resultados", resultados);
	    return "ejemplares/filtrarEjemplares";
	}
	
	@GetMapping("/verMensajes")
	public String verMensajesEjemplar(@RequestParam(name = "id", required = false) Long id, Model model) {
	    if (id == null) {
	        return "redirect:/ejemplares/filtrarEjemplares";
	    }

	    Ejemplares ejemplar = ejemplaresRepository.findByIdConMensajesYPersonas(id)
	        .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));

	    List<Mensajes> mensajesOrdenados = ejemplar.getMensajes().stream()
	        .sorted((m1, m2) -> m1.getFechahora().compareTo(m2.getFechahora()))
	        .toList();

	    model.addAttribute("ejemplar", ejemplar);
	    model.addAttribute("mensajes", mensajesOrdenados);

	    return "ejemplares/verMensajes";
	}






}
