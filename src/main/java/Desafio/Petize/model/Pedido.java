package Desafio.Petize.model;

import Desafio.Petize.enums.StatusPedido;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataPedido;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPedido statusPedido;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido () {
        return dataPedido;
    }

    public void setDataPedido (LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<Produto> getProdutos () {
        return produtos;
    }

    public void setProdutos (List<Produto> produtos) {
        this.produtos = produtos;
    }

    public StatusPedido getStatusPedido () {
        return statusPedido;
    }

    public void setStatusPedido (StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
