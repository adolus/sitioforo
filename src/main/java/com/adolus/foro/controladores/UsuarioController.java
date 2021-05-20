package com.adolus.foro.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adolus.foro.entidades.Usuario;
import com.adolus.foro.servicios.ServicioTema;
import com.adolus.foro.servicios.ServicioUsuario;

@Controller
public class UsuarioController {
	
	@Autowired
	ServicioUsuario servicioUsuario;
	@Autowired
	ServicioTema servicioTema;
	 
	@GetMapping(path = {"/usuario/validar", "/"})
	public String validar() {
		return "usuario/validar";
	}
	
	@PostMapping("/usuario/validar")
	public String agregar(@RequestParam("username") String correoe,
			@RequestParam("password") String clave, 
			Model modelo, HttpSession session) {
		//boolean res = servicioUsuario.validar(correoe, clave, session);
		//if(res) {

			return "redirect:/temas/listar";
		//}
		/*modelo.addAttribute("error", servicioUsuario.getMensaje());
		modelo.addAttribute("username", correoe);
		modelo.addAttribute("password", clave);
		return "/usuario/validar";*/
	}
	
	@GetMapping("/usuario/agregar")
	public String agregar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/agregar";
	}
	
	@PostMapping("/usuario/agregar")
	public String agregar(Model modelo, @ModelAttribute Usuario user, @RequestParam(value = "clave", required = false) String clave, HttpSession sesion) {
		boolean res = servicioUsuario.agregar(user, clave, sesion);
		if(res) {
			return "redirect:/temas/listar";
		}
		modelo.addAttribute("error", servicioUsuario.getMensaje());
		return "usuario/agregar";
	}
	
	@GetMapping("/usuario/salir")
	public String salir(HttpSession sesion) {
		SecurityContextHolder.clearContext();
		sesion.invalidate();
		return "redirect:/";
	}
}
