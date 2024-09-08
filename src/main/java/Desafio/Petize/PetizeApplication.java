package Desafio.Petize;

import Desafio.Petize.enums.StatusPedido;
import Desafio.Petize.model.Pedido;
import Desafio.Petize.model.Produto;
import Desafio.Petize.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan (basePackages = "Desafio.Petize")
public class PetizeApplication {

	@Autowired
	ProdutoRepository produtoRepository;

	public static void main(String[] args) {

		SpringApplication.run(PetizeApplication.class, args);
	}
}