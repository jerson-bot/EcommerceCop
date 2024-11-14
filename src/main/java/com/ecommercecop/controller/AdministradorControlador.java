package com.ecommercecop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Producto;
import com.ecommercecop.service.ProductoServicio;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping("")
	public String Home(Model modelo) {
		
		List<Producto> productos = productoServicio.findAll();
		modelo.addAttribute("productos", productos);
		return "administrador/Home";
	}

}
