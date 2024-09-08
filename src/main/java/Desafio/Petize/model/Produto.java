package Desafio.Petize.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5,  max = 40)
    @Column(length = 40, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;


    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco () {
        return preco;
    }

    public void setPreco (BigDecimal preco) {
        this.preco = preco;
    }
}
