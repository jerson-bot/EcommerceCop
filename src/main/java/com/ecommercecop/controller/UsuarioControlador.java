package com.ecommercecop.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Usuarios;
import com.ecommercecop.service.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/registro")
	public String Crear() {
		return "usuario/registro";
	}
	
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(Usuarios usuario) {
		logger.info("Usuario registro: {}",usuario);
		usuario.setTipo("USUARIO");
		usuarioServicio.guardar(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/inicioSesion")
	public String inicioSesion() {
		return "usuario/login";
	}
	
	@PostMapping("/acceso")
	public String acceso(Usuarios usuario, HttpSession session) {
		logger.info("Acesso: {}",usuario);
		Optional<Usuarios> user = usuarioServicio.findByEmail(usuario.getEmail());
		if(user.isPresent()) {
			session.setAttribute("IdUsuario", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
				logger.info("Usuario no existe");
			}
				
		
		return "redirect:/";
	}

	
}
