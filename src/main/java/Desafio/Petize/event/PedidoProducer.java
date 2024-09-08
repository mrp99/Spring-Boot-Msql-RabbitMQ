package Desafio.Petize.event;

import Desafio.Petize.model.Pedido;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PedidoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    public void sendMessage(@RequestParam Pedido pedido) {
        System.out.println(queue);
        rabbitTemplate.convertAndSend(queue, pedido);
        System.out.println("**** mensagem enviada"); //TODO LOG4J
    }
}