package com.ecommercecop.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommercecop.model.Producto;
import com.ecommercecop.service.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {
	
	private final Logger log= LoggerFactory.getLogger(HomeControlador.class);
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping("")
	 public String home(Model modelo) {
		modelo.addAttribute("productos", productoServicio.findAll());
		 return "usuario/home";
	 }
	@GetMapping("productohome/{Id}")
	public String productoHome(@PathVariable Integer Id, Model modelo) {
		log.info("Id enviado como dato/param {}", Id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoServicio.get(Id);
		producto = productoOptional.get();
		modelo.addAttribute("producto", producto);
		return "usuario/productohome";
	}
	
	@PostMapping("/carrito")
	public String AgregarCarrito() {
		return "usuario/carrito" ;
	}
}
