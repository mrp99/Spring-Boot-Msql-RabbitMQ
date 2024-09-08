package Desafio.Petize.event;

import Desafio.Petize.model.Pedido;
import Desafio.Petize.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "pedidos")
    public void putStatusPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
        System.out.println("**** messagem recebida/alterado: " + pedido.toString());
    }
}