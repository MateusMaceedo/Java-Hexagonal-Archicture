package com.example.springboot.application.adapters.api.controllers;

import com.example.springboot.application.adapters.api.ProdutoAPI;
import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.ports.interfaces.BuscarProdutosInterface;
import com.example.springboot.domain.ports.interfaces.ConsultarProdutosPorIdInterface;
import com.example.springboot.domain.ports.interfaces.CriarProdutosInterface;
import com.example.springboot.domain.ports.interfaces.AtualizarEstoqueInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ProdutoController implements ProdutoAPI {

    private final AtualizarEstoqueInterface atualizarEstoqueInterface;
    private final BuscarProdutosInterface buscarProdutosInterface;
    private final CriarProdutosInterface criarProdutosInterface;
    private final ConsultarProdutosPorIdInterface consultarProdutosPorIdInterface;

    @PostMapping
    public void criarProdutos(@RequestBody ProdutoDTO produtoDTO) {
        criarProdutosInterface.criarProduto(produtoDTO);
    }

    @GetMapping
    public List<ProdutoDTO> getProdutos() {
        return buscarProdutosInterface.buscarProdutos();
    }

    @GetMapping(value = "/{id}")
    public List<ProdutoDTO> getProdutosPorId(@PathVariable UUID id) { return consultarProdutosPorIdInterface.buscarProdutos(id); }

    @PutMapping(value = "/{sku}")
    public void atualizarEstoque(@PathVariable String sku, @RequestBody EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException {
        atualizarEstoqueInterface.atualizarEstoque(sku, estoqueDTO);
    }
}
