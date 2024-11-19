package com.ecommercecop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.service.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping("")
	 public String home(Model modelo) {
		modelo.addAttribute("productos", productoServicio.findAll());
		 return "usuario/home";
	 }
}
