package com.example.springboot.application.adapters.api.controllers;

import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.ports.interfaces.AtualizarEstoqueInterface;
import com.example.springboot.domain.ports.interfaces.BuscarProdutosInterface;
import com.example.springboot.domain.ports.interfaces.ConsultarProdutosPorIdInterface;
import com.example.springboot.domain.ports.interfaces.CriarProdutosInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProdutoControllerTest {

    @Mock
    private AtualizarEstoqueInterface atualizarEstoqueInterface;
    @Mock
    private BuscarProdutosInterface buscarProdutosInterface;
    @Mock
    private CriarProdutosInterface criarProdutosInterface;
    @Mock
    private ConsultarProdutosPorIdInterface consultarProdutosPorIdInterface;
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoController = new ProdutoController(
                atualizarEstoqueInterface,
                buscarProdutosInterface,
                criarProdutosInterface,
                consultarProdutosPorIdInterface
        );
    }

    @Test
    void criarProdutos_deveChamarMetodoCriarProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoController.criarProdutos(produtoDTO);

        verify(criarProdutosInterface).criarProduto(produtoDTO);
    }

    @Test
    void getProdutos_deveRetornarListaDeProdutos() {
        List<ProdutoDTO> produtos = new ArrayList<>();
        when(buscarProdutosInterface.buscarProdutos()).thenReturn(produtos);

        List<ProdutoDTO> result = produtoController.getProdutos();

        verify(buscarProdutosInterface).buscarProdutos();
        assertEquals(produtos, result);
    }

    @Test
    void getProdutosPorId_deveRetornarListaDeProdutosPorId() {
        UUID id = UUID.randomUUID();
        List<ProdutoDTO> produtos = new ArrayList<>();
        when(consultarProdutosPorIdInterface.buscarProdutos(id)).thenReturn(produtos);

        List<ProdutoDTO> result = produtoController.getProdutosPorId(id);

        verify(consultarProdutosPorIdInterface).buscarProdutos(id);
        assertEquals(produtos, result);
    }

    @Test
    void atualizarEstoque_deveChamarMetodoAtualizarEstoque() throws ChangeSetPersister.NotFoundException {
        String sku = "SKU123";
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        produtoController.atualizarEstoque(sku, estoqueDTO);

        verify(atualizarEstoqueInterface).atualizarEstoque(sku, estoqueDTO);
    }
}
