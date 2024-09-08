package Desafio.Petize.services;

import Desafio.Petize.enums.StatusPedido;
import Desafio.Petize.exception.ResourceNotFoundException;
import Desafio.Petize.model.Pedido;
import Desafio.Petize.model.Produto;
import Desafio.Petize.repository.PedidoRepository;
import Desafio.Petize.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private ProdutoRepository produtoRepository;


    public Pedido criarPedido(Pedido pedido) {
        List<Produto> produtosCarregados = pedido.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Produto com ID " + produto.getId() + " não encontrado"
                        ))).collect(Collectors.toList());
        pedido.setProdutos(produtosCarregados);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException (
                        "Pedido com ID " + id + " não encontrado"
                ));
    }



    public Pedido atualizarPedido(Pedido pedidoExistente) {
        return pedidoRepository.save(pedidoExistente);
    }

    public void deletarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido não encontrado com ID: " + id)
                );
        pedidoRepository.delete(pedido);
    }

    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            Pedido pedidoExistente = pedido.get();
            pedidoExistente.setStatusPedido(novoStatus);
            return pedidoRepository.save(pedidoExistente);
        }
        throw new EntityNotFoundException("Pedido não encontrado");
    }
}
