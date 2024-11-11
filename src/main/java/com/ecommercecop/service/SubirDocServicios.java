package com.ecommercecop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubirDocServicios {
	private String CarpetaImg ="Images//";
	
	public String guardarImagenes(MultipartFile archivo) throws IOException {
		if (!archivo.isEmpty()) {
			byte[] bytesImg = archivo.getBytes();
			Path ruta = Paths.get(CarpetaImg+archivo.getOriginalFilename());
			Files.write(ruta, bytesImg);
			return archivo.getOriginalFilename();
		}
		return "default.jpg";	 
	}
	
	public void borrarImagen(String nombre) {
		String rutaImagen = "Images//";
		File archivo = new File(rutaImagen+nombre);
		archivo.delete();
	}

}
