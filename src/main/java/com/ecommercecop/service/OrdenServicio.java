package com.ecommercecop.service;

import java.util.List;

import com.ecommercecop.model.Orden;

public interface OrdenServicio {
	List<Orden> findAll();
	Orden guardar(Orden orden);
	String CreacionNumeroOrden();

}
