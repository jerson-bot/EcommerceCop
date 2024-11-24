package com.ecommercecop.service;

import java.util.List;
import java.util.Optional;


import com.ecommercecop.model.Usuarios;

public interface UsuarioServicio {
	List<Usuarios> findAll();
	Optional<Usuarios> findById(Integer id);
	Usuarios guardar(Usuarios usuario);
	Optional<Usuarios> findByEmail(String Email);
}
