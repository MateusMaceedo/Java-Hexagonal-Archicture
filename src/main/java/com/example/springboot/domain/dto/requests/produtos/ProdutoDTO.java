package com.example.springboot.domain.dto.requests.produtos;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
@Data
public class ProdutoDTO {

    private UUID codigo;
    private String sku;
    private String nome;
    private Double preco;
    private Double quantidade;

    public ProdutoDTO(UUID codigo, String sku, String nome, Double preco, Double quantidade) {
        this.codigo = codigo;
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ProdutoDTO() {

    }
}
