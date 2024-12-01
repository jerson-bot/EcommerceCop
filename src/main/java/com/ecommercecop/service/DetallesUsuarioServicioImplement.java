package com.ecommercecop.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommercecop.model.Usuarios;

import jakarta.servlet.http.HttpSession;

@Service
public class DetallesUsuarioServicioImplement implements UserDetailsService {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	@Autowired
	private HttpSession session;
	
	private Logger logger = LoggerFactory.getLogger(DetallesUsuarioServicioImplement.class);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		logger.info("Este es el usuario: {} ",username);
		Optional<Usuarios> optionalUsuario = usuarioServicio.findByEmail(username);
		if (optionalUsuario.isPresent()) {
	           Usuarios usuario = optionalUsuario.get();
	            logger.info("Usuario encontrado: Id {}", usuario.getId());
	            session.setAttribute("IdUsuario", usuario.getId());
	            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	            return User.builder()
	                    .username(usuario.getNombre())
	                    .password(bCrypt.encode(usuario.getContra())) // Encriptar o comparar contraseñas
	                    .roles(usuario.getTipo())
	                    .build();
			
		}else{
				throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
	}

}
