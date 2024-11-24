package com.ecommercecop.service;

import java.util.List;
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
	public Optional<Usuarios> findById(Integer id) {
		return usuarioRepositorio.findById(id);
	}

	@Override
	public Usuarios guardar(Usuarios usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Optional<Usuarios> findByEmail(String Email) {
		return usuarioRepositorio.findByEmail(Email);
	}

	@Override
	public List<Usuarios> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




}
