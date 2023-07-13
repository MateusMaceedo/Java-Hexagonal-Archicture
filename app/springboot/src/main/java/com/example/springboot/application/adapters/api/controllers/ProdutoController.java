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
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
@RestController
public class ProdutoController implements ProdutoAPI {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final AtualizarEstoqueInterface atualizarEstoqueInterface;
    private final BuscarProdutosInterface buscarProdutosInterface;
    private final CriarProdutosInterface criarProdutosInterface;
    private final ConsultarProdutosPorIdInterface consultarProdutosPorIdInterface;

    @Value("${cloud.aws.fila.compra_cartao_credito}")
    private String uriCompraCartaoCredito;

    @PostMapping
    public void criarProdutos(@RequestBody ProdutoDTO produtoDTO) {
        queueMessagingTemplate.send(uriCompraCartaoCredito, MessageBuilder.withPayload(produtoDTO).build());
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
