package Desafio.Petize.services;

import Desafio.Petize.exception.ResourceNotFoundException;
import Desafio.Petize.model.Produto;
import Desafio.Petize.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto com ID " + id + " não encontrado")
                );
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) throw new ResourceNotFoundException(
                "Produto com ID " + id + " não encontrado"
        );
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id))  throw new ResourceNotFoundException(
                "Produto com ID " + id + " não encontrado"
        );
        produtoRepository.deleteById(id);
    }

}
