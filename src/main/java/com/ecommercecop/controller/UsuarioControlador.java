package com.ecommercecop.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Usuarios;
import com.ecommercecop.service.UsuarioServicio;

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
	
}
