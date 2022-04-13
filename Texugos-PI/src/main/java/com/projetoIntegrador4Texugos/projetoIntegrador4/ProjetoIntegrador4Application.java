package com.projetoIntegrador4Texugos.projetoIntegrador4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.TipoUsuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.model.Usuario;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.UsuarioRepository;
import com.projetoIntegrador4Texugos.projetoIntegrador4.service.UploadImagemService;

@SpringBootApplication
public class ProjetoIntegrador4Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoIntegrador4Application.class, args);
	}
	
	@Bean
    public CommandLineRunner demoData(UsuarioRepository usu) {
        return args -> { 

        	Usuario usuario = new Usuario(1, "Administrador", "administrador@texugos.com", 11111111111L, 
        			new BCryptPasswordEncoder().encode("admin1234"),"999.999.999-99", "2022-03-01", true, TipoUsuario.ADMINISTRADOR);
        	usu.save(usuario);
        };
    }
	
	@Bean
	CommandLineRunner init(UploadImagemService imageService) {
		return (args) -> {
			imageService.deleteAll();
			imageService.init();
		};
	}
}
