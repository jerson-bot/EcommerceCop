package com.ecommercecop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	
}
