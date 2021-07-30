package com.project.gestaoFesta;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestaoFestaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoFestaApplication.class, args);
	}
	
	//Adicionando o Swagger na aplicação, foi colocado o apache 2.0 como meio de demonstração apenas
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().info(new Info()
				.title("Gestão Festa API")
				.version("1.0")
				.termsOfService("https://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")))	;
	}
}
