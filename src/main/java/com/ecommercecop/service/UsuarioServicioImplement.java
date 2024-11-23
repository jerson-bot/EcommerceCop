package com.ecommercecop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.Usuarios;
import com.ecommercecop.repository.UsuarioRepositorio;

@Service
public class UsuarioServicioImplement implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Optional<Usuarios> findById(Integer Id) {
		return usuarioRepositorio.findById(Id);
	}	
	

}
