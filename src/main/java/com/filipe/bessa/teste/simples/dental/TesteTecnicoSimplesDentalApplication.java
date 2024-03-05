package com.filipe.bessa.teste.simples.dental;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(
		title="${info.app.name:teste}",
		version = "${info.app.version:0.0.0}",
		description = "${info.app.description:undescribed}",
		contact = @Contact(
				name = "${info.app.contact.name}",
				email = "${info.app.contact.email}"
		)
))
public class TesteTecnicoSimplesDentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteTecnicoSimplesDentalApplication.class, args);
	}

}
