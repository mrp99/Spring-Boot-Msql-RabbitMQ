package Desafio.Petize.controller;

import Desafio.Petize.enums.StatusPedido;
import Desafio.Petize.exception.ResourceNotFoundException;
import Desafio.Petize.model.Pedido;
import Desafio.Petize.repository.PedidoRepository;
import Desafio.Petize.services.PedidoService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.buscarPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid Pedido pedido) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable Long id,
            @RequestBody @Valid Pedido pedidoAtualizado) {
        try {
            Pedido pedidoExistente  = pedidoService.buscarPedidoPorId(id);

            pedidoExistente.setDataPedido(pedidoAtualizado.getDataPedido());
            pedidoExistente.setStatusPedido(pedidoAtualizado.getStatusPedido());

            Pedido pedidoSalvo = pedidoService.atualizarPedido(pedidoExistente);
            return ResponseEntity.ok(pedidoSalvo);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return ResponseEntity.noContent().build();

        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> alterarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status
    ) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, status);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
