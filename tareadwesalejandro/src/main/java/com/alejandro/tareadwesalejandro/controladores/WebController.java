package com.alejandro.tareadwesalejandro.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String index() {
		return "index"; 
	}

	@GetMapping("/login")
	public String login() {
		return "login"; // login.html
	}

	@GetMapping("/home")
	public String home(Authentication auth, Model model) {
		if (auth == null) {
			return "redirect:/login";
		}

		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "redirect:/admin";
		} else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PERSONAL"))) {
			return "redirect:/personal";
		}

		return "home"; 
	}

	@GetMapping("/admin")
	public String admin() {
		return "/perfiles/admin"; // admin.html
	}

	@GetMapping("/personal")
	public String personal() {
		return "/perfiles/personal"; // personal.html
	}

	@GetMapping("/invitado")
	public String invitado() {
		return "/perfiles/invitado"; // invitado.html
	}

}
