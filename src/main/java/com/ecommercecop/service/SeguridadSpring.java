package com.ecommercecop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import com.ecommercecop.model.Usuarios;
import com.ecommercecop.repository.UsuarioRepositorio;

@Configuration
@EnableWebSecurity
public class SeguridadSpring {

	 private final DetallesUsuarioServicioImplement detallesUsuarioServicio;

	    public SeguridadSpring(DetallesUsuarioServicioImplement detallesUsuarioServicio) {
	        this.detallesUsuarioServicio = detallesUsuarioServicio;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para simplificar pruebas; 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/administrador/**").hasRole("ADMIN")
	                .requestMatchers("/Producto/**").hasRole("ADMIN")
	                .anyRequest().permitAll()
	            )
	            .formLogin(form -> form
	                .loginPage("/usuario/inicioSesion")
	                .permitAll()
	                .defaultSuccessUrl("/usuario/acceso")
	            )
	            .logout(logout -> logout
	                .logoutUrl("/usuario/logout")
	                .logoutSuccessUrl("/usuario/login?logout")
	                .permitAll()
	            );
	        return http.build();
	    }
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
    }
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SeguridadSpring {
	
	@Autowired
	private final DetallesUsuarioServicioImplement detallesUsuarioServicio;
	
	
	public SeguridadSpring(DetallesUsuarioServicioImplement detallesUsuarioServicio){
	        this.detallesUsuarioServicio = detallesUsuarioServicio;
	    }

	
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http.csrf(csrf -> csrf.disable()) // Deshabilita CSRF para simplificar pruebas; 
         .authorizeHttpRequests(auth -> auth
 		                .requestMatchers("/administrador/**").hasRole("ADMIN")
 		                .requestMatchers("/Producto/**").hasRole("ADMIN")
 		                .anyRequest().permitAll()
 		            )
 		            .formLogin(form -> form
 		                .loginPage("/usuario/inicioSesion")
 		                .permitAll()
 		                .defaultSuccessUrl("/usuario/acceso", true)
 		            )
 		            .logout(logout -> logout
 		                .logoutUrl("/usuario/logout")
 		                .logoutSuccessUrl("/usuario/login?logout")
 		                .permitAll()
 		            );
 		        return http.build();
 		    }
	
	
	@Bean
	public BCryptPasswordEncoder getEnecoder() {
		return new BCryptPasswordEncoder();
	}
	
}*/