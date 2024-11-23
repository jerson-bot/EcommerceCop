package com.ecommercecop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.Orden;
import com.ecommercecop.repository.OrdenRepositorio;

@Service

public class OrdenServicioImplement implements OrdenServicio {

	@Autowired
	private OrdenRepositorio ordenRepositorio;
	
	@Override
	public Orden guardar(Orden orden) {
		
		return ordenRepositorio.save(orden);
	}

}
