package com.ecommercecop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.Orden;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

	
}
