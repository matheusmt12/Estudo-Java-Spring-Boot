package com.matheusmt.dev.ProjetoAprendizagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjetoAprendizagemApplication {

	@Autowired
	private AppConfiguration appConfiguration;
	
	public ProjetoAprendizagemApplication(AppConfiguration configuration) {
		this.appConfiguration = configuration;
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoAprendizagemApplication.class, args);
	}

	@GetMapping("/inicio")
	public String inicio() {
		return "Ola mundo!!";
	}

	
	@GetMapping("configuracao")
	public String Config() {
		return this.appConfiguration.getMensagem();
	}

}
