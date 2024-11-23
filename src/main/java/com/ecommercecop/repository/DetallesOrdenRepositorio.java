package com.ecommercecop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.DetallesOrden;

@Repository
public interface DetallesOrdenRepositorio extends JpaRepository<DetallesOrden, Integer> {

}
