package com.example.springboot.domain.ports.interfaces;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import java.util.List;
import java.util.UUID;

public interface ConsultarProdutosPorIdInterface {
    List<ProdutoDTO> buscarProdutos(UUID id);
}
