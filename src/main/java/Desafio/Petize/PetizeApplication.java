package Desafio.Petize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = "Desafio.Petize")
public class PetizeApplication {

	public static void main(String[] args) {

		SpringApplication.run(PetizeApplication.class, args);
	}

}
