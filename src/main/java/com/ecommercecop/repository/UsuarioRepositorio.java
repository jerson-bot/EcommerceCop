package com.ecommercecop.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercecop.model.Usuarios;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer>{
	@Query(value = "SELECT * FROM Usuarios where email = :Email", nativeQuery = true)
	Optional<Usuarios> findByEmail(@Param("Email") String Email);
}

