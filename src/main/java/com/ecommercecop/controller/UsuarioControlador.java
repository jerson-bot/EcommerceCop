package com.ecommercecop.controller;


import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Usuarios;
import com.ecommercecop.service.OrdenServicio;
import com.ecommercecop.service.UsuarioServicio;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private OrdenServicio ordenServicio;
	
	//BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping("/registro")
	public String Crear() {
		return "usuario/registro";
	}
	
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(Usuarios usuario) {
		logger.info("Usuario registro: {}",usuario);
		usuario.setTipo("USUARIO");
		usuario.setContra(usuario.getContra());

		//usuario.setContra(passEncode.encode(usuario.getContra()));
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
	        logger.info("ID del usuario guardado en sesión: {}", session.getAttribute("IdUsuario"));
			String tipoUsuario = user.get().getTipo();
	        if(tipoUsuario.equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
				logger.info("Usuario no encontrado {}",usuario.getEmail());
			}		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String compras(Model modelo,HttpSession session) {
		modelo.addAttribute("sesion", session.getAttribute("IdUsuario"));
		//para obtener usuario y las ordenes 
		Usuarios usuario = usuarioServicio.findById(Integer.parseInt(session.getAttribute("IdUsuario").toString())).get();
		List<Orden> ordenes = ordenServicio.findByUsuarios(usuario);
		
		modelo.addAttribute("ordenes", ordenes);
		return "usuario/compras";
	}
	
	@GetMapping("/detalleCompra/{Id}")
	public String detalleCompra(@PathVariable Integer Id, HttpSession session, Model modelo) {
		//visualización del id de la orden por consola
		logger.info("Id de la orden: {}", Id);
		Optional<Orden> orden = ordenServicio.findById(Id);
		modelo.addAttribute("detalles", orden.get().getDetalle());
		modelo.addAttribute("sesion", session.getAttribute("IdUsuario"));
		return "usuario/detallecompra";
	}
	
	@GetMapping("/cerrarS")
	public String cerrarS(HttpSession session) {
		session.removeAttribute("IdUsuario");
		return "redirect:/" ;
	}
	
}
