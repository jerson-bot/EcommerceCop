package com.ecommercecop.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Nombre;
	private String User;
	private String Email;
	private String Direccion;
	private String Telefono;
	private String Tipo;
	private String Contra;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Producto> Productos;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Orden> Ordenes;
	
	public Usuarios() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuarios(Integer id, String nombre, String user, String email, String direccion, String telefono,
			String tipo, String contra) {
		super();
		Id = id;
		Nombre = nombre;
		User = user;
		Email = email;
		Direccion = direccion;
		Telefono = telefono;
		Tipo = tipo;
		Contra = contra;
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


	public String getUser() {
		return User;
	}


	public void setUser(String user) {
		User = user;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getDireccion() {
		return Direccion;
	}


	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}


	public String getTipo() {
		return Tipo;
	}


	public void setTipo(String tipo) {
		Tipo = tipo;
	}


	public String getContra() {
		return Contra;
	}


	public void setContra(String contra) {
		Contra = contra;
	}

	public List<Producto> getProductos() {
		return Productos;
	}

	public void setProductos(List<Producto> productos) {
		Productos = productos;
	}

	@Override
	public String toString() {
		return "Usuarios [Id=" + Id + ", Nombre=" + Nombre + ", User=" + User + ", Email=" + Email + ", Direccion="
				+ Direccion + ", Telefono=" + Telefono + ", Tipo=" + Tipo + ", Contra=" + Contra + ", Productos="
				+ Productos + ", Ordenes=" + Ordenes + "]";
	}



	
	
		
	

}
