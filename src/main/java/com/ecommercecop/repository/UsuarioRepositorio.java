package com.ecommercecop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.Usuarios;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer>{

}
