package com.alejandro.tareadwesalejandro.controladores;

import com.alejandro.tareadwesalejandro.dto.RegistroPersonaDTO;
import com.alejandro.tareadwesalejandro.modelo.Personas;
import com.alejandro.tareadwesalejandro.servicios.ServiciosCredenciales;
import com.alejandro.tareadwesalejandro.servicios.ServiciosPersonas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/personas")
public class PersonasController {

	@Autowired
	private ServiciosPersonas serviciosPersonas;
	
	@Autowired
	private ServiciosCredenciales serviciosCredenciales;

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

	@GetMapping("/registrar")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("registro", new RegistroPersonaDTO());
		return "personas/registrarPersona"; // registrarPersona.html
	}

	@PostMapping("/registrar")
	public String procesarRegistro(@ModelAttribute("registro") RegistroPersonaDTO dto, Model model,
			RedirectAttributes redirectAttributes) {

		if (serviciosPersonas.emailExiste(dto.getEmail())) {
			model.addAttribute("error", "El correo ya está registrado.");
			return "personas/registrarPersona";
		}

		if (serviciosCredenciales.usuarioExiste(dto.getUsuario())) {
			model.addAttribute("error", "El nombre de usuario ya existe.");
			return "personas/registrarPersona";
		}

		serviciosPersonas.registrarPersonaConCredenciales(dto);
		redirectAttributes.addFlashAttribute("mensaje", "La persona ha sido registrada correctamente.");
		return "redirect:/personas/registrar";
	}

}
