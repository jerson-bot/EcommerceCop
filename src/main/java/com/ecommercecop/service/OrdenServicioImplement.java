package com.ecommercecop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.Orden;
import com.ecommercecop.model.Usuarios;
import com.ecommercecop.repository.OrdenRepositorio;

@Service

public class OrdenServicioImplement implements OrdenServicio {

	@Autowired
	private OrdenRepositorio ordenRepositorio;
	
	@Override
	public Orden guardar(Orden orden) {
		
		return ordenRepositorio.save(orden);
	}

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return ordenRepositorio.findAll();
	}
// obtener todas las ordenes y obtener el ultimo número ingresado de la ultima orden
	public String CreacionNumeroOrden() {
		int Numero = 0;
		String concatenacionNumero ="";
		List<Orden> ordenes = findAll();
		List<Integer> Num = new ArrayList<Integer>();
		ordenes.stream().forEach(o -> Num.add(Integer.parseInt(o.getNumero())));
		if (ordenes.isEmpty()) {
			Numero = 1;
		}else {
			Numero = Num.stream().max(Integer::compare).get();
			Numero++;
		}
		if (Numero<10) {
			concatenacionNumero = "000000000"+String.valueOf(Numero);
		}else if (Numero<100) {
			concatenacionNumero = "00000000"+String.valueOf(Numero);
		}else if (Numero<1000) {
			concatenacionNumero = "0000000"+String.valueOf(Numero);
		}else if (Numero<10000) {
			concatenacionNumero = "000000"+String.valueOf(Numero);
		}else {
			concatenacionNumero = "0";
		}
		
		
		return concatenacionNumero;
	}

	@Override
	public List<Orden> findByUsuarios(Usuarios usuario) {
		
		return ordenRepositorio.findByUsuarios(usuario);
	}

	@Override
	public Optional<Orden> findById(Integer Id) {
		return ordenRepositorio.findById(Id);
	}
}

