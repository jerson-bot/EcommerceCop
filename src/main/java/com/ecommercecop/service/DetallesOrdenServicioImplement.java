package com.ecommercecop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.DetallesOrden;
import com.ecommercecop.repository.DetallesOrdenRepositorio;

@Service
public class DetallesOrdenServicioImplement implements DetallesOrdenServicio {

	@Autowired
	private DetallesOrdenRepositorio detallesOrdenRepositorio;
	
	@Override
	public DetallesOrden guardar(DetallesOrden detallesOrden) {
		return detallesOrdenRepositorio.save(detallesOrden);
	}

}
