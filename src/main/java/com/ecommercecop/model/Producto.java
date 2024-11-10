package com.ecommercecop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name= "Productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Nombre;
	private String Descripcion;
	private String ImgProducto;
	private double Precio;
	private int Cantidad;
	
	@ManyToOne
	private Usuarios usuarios;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	
	public Producto(Integer id, String nombre, String descripcion, String imgProducto, double precio, int cantidad,
			Usuarios usuarios) {
		super();
		Id = id;
		Nombre = nombre;
		Descripcion = descripcion;
		ImgProducto = imgProducto;
		Precio = precio;
		Cantidad = cantidad;
		this.usuarios = usuarios;
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
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getImgProducto() {
		return ImgProducto;
	}
	public void setImgProducto(String imgProducto) {
		ImgProducto = imgProducto;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	public Usuarios getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}


	@Override
	public String toString() {
		return "Producto [Id=" + Id + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", ImgProducto="
				+ ImgProducto + ", Precio=" + Precio + ", Cantidad=" + Cantidad + "]";
	}

	
	
}
