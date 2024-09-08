package Desafio.Petize.repository;

import Desafio.Petize.enums.StatusPedido;
import Desafio.Petize.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatusPedido(StatusPedido status);
}
