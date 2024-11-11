package com.ecommercecop.service;

import java.util.Optional;

import com.ecommercecop.model.Producto;

public interface ProductoServicio {

	public Producto guardar(Producto productos);
	public Optional<Producto> get(Integer Id); 
	public void actualizar(Producto productos);
	public void borrar(Integer Id);
}
