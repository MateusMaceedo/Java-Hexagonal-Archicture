package com.example.springboot.application.adapters.api;

import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "produtos")
public interface ProdutoAPI {
    void criarProdutos(@RequestBody ProdutoDTO produtoDTO);
    List<ProdutoDTO> getProdutos();
    List<ProdutoDTO> getProdutosPorId(UUID id);
    void atualizarEstoque(@PathVariable String sku, @RequestBody EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException;
}
