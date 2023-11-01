package com.matheusmt.dev.ProjetoAprendizagem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	public String getMensagem() {
		return "Mensagem configuraçõ";
	}

	
	@Bean
	public void init() {
		System.out.println("teste iniciado ");
	}
}
