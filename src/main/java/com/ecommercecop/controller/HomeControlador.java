package com.ecommercecop.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercecop.model.DetallesOrden;
import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Producto;
import com.ecommercecop.service.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {
	
	private final Logger log= LoggerFactory.getLogger(HomeControlador.class);
	
	//Detalles del producto pedido
	List<DetallesOrden> Detalles = new ArrayList<DetallesOrden>();
	
	//datos del producto
	Orden orden = new Orden();
	
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
	public String AgregarCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model modelo) {
		DetallesOrden detallesOrden = new DetallesOrden();
		Producto producto = new Producto();
		double sumaTotal = 0 ;
		
		Optional<Producto> ProductoOpcional = productoServicio.get(id);
	//estas dos lineas son para validar el envio de datos de la vista de producto, al carrito
		//log.info("Producto añadido: {}", ProductoOpcional.get());
		//log.info("Cantidad: {}", cantidad);
		producto = ProductoOpcional.get();
		detallesOrden.setCantidad(cantidad);
		detallesOrden.setPrecio(producto.getPrecio());
		detallesOrden.setNombre(producto.getNombre());
		detallesOrden.setTotal(producto.getPrecio()*cantidad);
		detallesOrden.setProductos(producto);
		
		//funcion para que no se repitan más de un valor cuando se agregue
		Integer IdProducto = producto.getId();
		boolean ProdIngresado = Detalles.stream().anyMatch(Prod -> Prod.getProductos().getId()==IdProducto) ;
		if (!ProdIngresado) {
			Detalles.add(detallesOrden);
		}
		
		sumaTotal = Detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		modelo.addAttribute("carrito", Detalles);
		modelo.addAttribute("orden", orden);
		
		return "usuario/carrito" ;
	}
	@GetMapping("/obtenerCarrito")
	public String obtenerCarrito(Model modelo) {
		
		modelo.addAttribute("carrito", Detalles);
		modelo.addAttribute("orden", orden);
		return "/usuario/carrito";
	}
	
	@GetMapping("/orden")
	public String Orden() {
		
		return "usuario/verOrden";
	}
	
	@GetMapping("/borrar/carrito/{Id}")
	public String BorrarProductoCarrito(@PathVariable Integer Id, Model modelo) {
		
		List<DetallesOrden> ordenNueva = new ArrayList<DetallesOrden>();
		
		for(DetallesOrden detallesOrden:Detalles) {
			if (detallesOrden.getProductos().getId()!=Id) {
				ordenNueva.add(detallesOrden);
			}
			
		}
		//se guarda la lista de productos luego del borrado (productos restantes)
		Detalles = ordenNueva;
		
		double sumaTotal = 0;
		sumaTotal = Detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		modelo.addAttribute("carrito", Detalles);
		modelo.addAttribute("orden", orden);
		
		return "usuario/carrito";
	}
}
