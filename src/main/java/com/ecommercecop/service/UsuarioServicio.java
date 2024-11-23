package com.ecommercecop.service;

import java.util.Optional;

import com.ecommercecop.model.Usuarios;

public interface UsuarioServicio {
	Optional<Usuarios> findById(Integer Id);
}
