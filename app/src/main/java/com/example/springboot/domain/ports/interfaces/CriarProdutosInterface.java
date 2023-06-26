package com.example.springboot.domain.ports.interfaces;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;

public interface CriarProdutosInterface {
    void criarProduto(ProdutoDTO produtoDTO);
}
