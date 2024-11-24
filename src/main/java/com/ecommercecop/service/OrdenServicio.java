package com.ecommercecop.service;

import java.util.List;
import java.util.Optional;

import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Usuarios;

public interface OrdenServicio {
	List<Orden> findAll();
	Optional<Orden> findById(Integer Id);
	Orden guardar(Orden orden);
	String CreacionNumeroOrden();
	List<Orden> findByUsuarios(Usuarios usuario);

}
