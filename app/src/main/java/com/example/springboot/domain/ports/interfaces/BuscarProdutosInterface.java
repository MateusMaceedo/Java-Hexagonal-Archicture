package com.example.springboot.domain.ports.interfaces;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;

import java.util.List;

public interface BuscarProdutosInterface {
    List<ProdutoDTO> buscarProdutos();
}
