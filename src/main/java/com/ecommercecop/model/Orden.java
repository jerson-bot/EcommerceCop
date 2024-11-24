package com.ecommercecop.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Numero;
	private Date FechaCreacion;
	private Date FechaRecibida;
	private double Total;
	
	@ManyToOne
	private Usuarios usuarios;
	
	@OneToMany(mappedBy = "Orden")
	private List<DetallesOrden> detalle;
	
	public Orden() {
		// TODO Auto-generated constructor stub
	}

	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		Id = id;
		Numero = numero;
		FechaCreacion = fechaCreacion;
		FechaRecibida = fechaRecibida;
		Total = total;
	}




	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public Date getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return FechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		FechaRecibida = fechaRecibida;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public Usuarios getUsuario() {
		return usuarios;
	}

	public void setUsuario(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<DetallesOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallesOrden> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Orden [Id=" + Id + ", Numero=" + Numero + ", FechaCreacion=" + FechaCreacion + ", FechaRecibida="
				+ FechaRecibida + "]";
	}
	

}
