package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.ports.interfaces.BuscarProdutosInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BuscarProdutosUseCaseImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    private BuscarProdutosInterface buscarProdutosUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarProdutosUseCase = new BuscarProdutosUseCaseImpl(produtoRepository);
    }

    @Test
    void buscarProdutos_deveRetornarListaDeProdutosDTO() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto());
        produtos.add(new Produto());

        when(produtoRepository.buscarTodos()).thenReturn(produtos);

        List<ProdutoDTO> produtosDTO = buscarProdutosUseCase.buscarProdutos();

        assertEquals(2, produtosDTO.size());
    }

    @Test
    void buscarProdutos_deveLancarProductNotFoundException_quandoListaDeProdutosVazia() {
        when(produtoRepository.buscarTodos()).thenReturn(new ArrayList<>());

        assertThrows(ProductNotFoundException.class, () -> {
            buscarProdutosUseCase.buscarProdutos();
        });
    }
}
