package com.ecommercecop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Producto;
import com.ecommercecop.service.OrdenServicio;
import com.ecommercecop.service.ProductoServicio;
import com.ecommercecop.service.UsuarioServicio;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private OrdenServicio ordenServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	private Logger logger = LoggerFactory.getLogger(AdministradorControlador.class);
	
	@GetMapping("")
	public String Home(Model modelo) {
		
		List<Producto> productos = productoServicio.findAll();
		modelo.addAttribute("productos", productos);
		return "administrador/Home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model modelo) {
		modelo.addAttribute("usuarios", usuarioServicio.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model modelo) {
		modelo.addAttribute("ordenes", ordenServicio.findAll());
		
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalles/{Id}")
	public String detalles(Model modelo, @PathVariable Integer Id) {
		logger.info("Id de la orden: {}", Id);
		Orden orden = ordenServicio.findById(Id).get();
		modelo.addAttribute("detalles", orden.getDetalle());
		
		return "administrador/detalleorden";
	}
	
	
}
