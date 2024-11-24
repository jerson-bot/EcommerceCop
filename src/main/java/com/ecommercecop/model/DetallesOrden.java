package com.ecommercecop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "Detalles")
public class DetallesOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Nombre;
	private double Cantidad;
	private double Precio;
	private double Total;
	
	@ManyToOne
	private Orden Orden;
	
	@ManyToOne
	private Producto Productos;
	
	public DetallesOrden() {
		// TODO Auto-generated constructor stub
	}

	public DetallesOrden(Integer id, String nombre, double cantidad, double precio, double total) {
		super();
		Id = id;
		Nombre = nombre;
		Cantidad = cantidad;
		Precio = precio;
		Total = total;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public double getCantidad() {
		return Cantidad;
	}

	public void setCantidad(double cantidad) {
		Cantidad = cantidad;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}
	
	public Orden getOrden() {
		return Orden;
	}

	public void setOrden(Orden orden) {
		Orden = orden;
	}

	public Producto getProductos() {
		return Productos;
	}

	public void setProductos(Producto productos) {
		Productos = productos;
	}

	@Override
	public String toString() {
		return "DetallesOrden [Id=" + Id + ", Nombre=" + Nombre + ", Cantidad=" + Cantidad + ", Precio=" + Precio
				+ ", Total=" + Total + "]";
	}
	
	
}
