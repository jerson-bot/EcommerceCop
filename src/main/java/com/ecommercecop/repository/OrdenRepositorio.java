package com.ecommercecop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Usuarios;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {
	List<Orden> findByUsuarios(Usuarios usuario);
		
}
