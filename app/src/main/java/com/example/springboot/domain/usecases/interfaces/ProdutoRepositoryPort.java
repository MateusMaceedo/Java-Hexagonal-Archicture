package com.example.springboot.domain.usecases.interfaces;

import com.example.springboot.domain.mapper.Produto;
import java.util.List;
import java.util.UUID;

public interface ProdutoRepositoryPort {
    List<Produto> buscarPorId(UUID id);
    List<Produto> buscarTodos();
    Produto buscarPeloSku(String sku);
    void salvar(Produto produto);
}
