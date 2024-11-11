package com.ecommercecop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.Producto;
import com.ecommercecop.repository.ProductoRepositorio;

@Service
public class ProductoServicioImplement implements ProductoServicio{

	@Autowired
	private ProductoRepositorio productoRepositorio;
	@Override
	public Producto guardar(Producto productos) {
		// TODO Auto-generated method stub
		return productoRepositorio.save(productos);
	}

	@Override
	public Optional<Producto> get(Integer Id) {
		// TODO Auto-generated method stub
		return productoRepositorio.findById(Id);
	}

	@Override
	public void actualizar(Producto productos) {
		productoRepositorio.save(productos);
	}

	@Override
	public void borrar(Integer Id) {
		productoRepositorio.deleteById(Id);
	}
	
	
}


