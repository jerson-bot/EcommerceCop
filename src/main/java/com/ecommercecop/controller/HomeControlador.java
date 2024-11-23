package com.ecommercecop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.ecommercecop.model.Usuarios;
import com.ecommercecop.service.DetallesOrdenServicio;
import com.ecommercecop.service.OrdenServicio;
import com.ecommercecop.service.ProductoServicio;
import com.ecommercecop.service.UsuarioServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {
	
	private final Logger log= LoggerFactory.getLogger(HomeControlador.class);
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private OrdenServicio ordenServicio;
	
	@Autowired
	private DetallesOrdenServicio detallesOrdenServicio;
	
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
	public String Orden(Model modelo) {
		
		Usuarios usuario = usuarioServicio.findById(1).get();
		
		modelo.addAttribute("carrito", Detalles);
		modelo.addAttribute("orden", orden);
		modelo.addAttribute("usuario", usuario);
		return "/usuario/verOrden";
	}
	
	//Es para guardar la orden
	@GetMapping("/guardarOrden")
		public String guardarOrden() {
		Date fechaOrdenCreada = new Date();
		orden.setFechaCreacion(fechaOrdenCreada);
		orden.setNumero(ordenServicio.CreacionNumeroOrden());
		//usuario que hace la compra
		Usuarios usuario = usuarioServicio.findById(1).get();
		orden.setUsuario(usuario);
		ordenServicio.guardar(orden);
		
		//guardamos los detalles
		
		for (DetallesOrden dt:Detalles) {
			dt.setOrden(orden);
			detallesOrdenServicio.guardar(dt);
		}
		
		//Quitamos lo agregado en la orden para el usuario
		
		orden = new Orden();
		Detalles.clear();
		
		return "redirect:/";
	}
	
	@PostMapping("/buscar")
	public String buscarProductos(@RequestParam String NombreP,Model modelo) {
		log.info("El nombre del producto es: {} "+NombreP);
		// obtiene los productos, filtra los datos enviados, trae nombres y con el string traido, lo transformamos en lista
		List<Producto> producto = (List<Producto>) productoServicio.findAll().stream().filter(p -> p.getNombre().contains(NombreP)).collect(Collectors.toList());
		modelo.addAttribute("productos", producto);
		return "usuario/home";
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
