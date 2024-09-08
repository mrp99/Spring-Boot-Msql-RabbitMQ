//package Desafio.Petize.config;
//
//import Desafio.Petize.model.Produto;
//import Desafio.Petize.repository.ProdutoRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Configuration
//public class DataInicializer {
//
//
//    @Component
//    public class DataInitializer {
//
//        @Autowired
//        private ProdutoRepository produtoRepository;
//
//        @PostConstruct
//        public void init() {
//            // Inserir dados iniciais
//            produtoRepository.save(new Produto("admin", "password123"));
//            produtoRepository.save(new Produto("user", "password456"));
//        }
//    }
//}
