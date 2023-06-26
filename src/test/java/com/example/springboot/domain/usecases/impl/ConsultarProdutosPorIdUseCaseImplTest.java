package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.ports.interfaces.ConsultarProdutosPorIdInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ConsultarProdutosPorIdUseCaseImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    private ConsultarProdutosPorIdInterface consultarProdutosPorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consultarProdutosPorIdUseCase = new ConsultarProdutosPorIdUseCaseImpl(produtoRepository);
    }

    @Test
    void buscarProdutos_deveRetornarListaDeProdutosDTO() {
        UUID id = UUID.randomUUID();
        Produto produto1 = new Produto();
        produto1.setId(id);
        Produto produto2 = new Produto();
        produto2.setId(id);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);

        when(produtoRepository.buscarPorId(id)).thenReturn(produtos);

        List<ProdutoDTO> produtosDTO = consultarProdutosPorIdUseCase.buscarProdutos(id);

        assertEquals(2, produtosDTO.size());
        assertEquals(id, produtosDTO.get(0).getId());
        assertEquals(id, produtosDTO.get(1).getId());
    }

    @Test
    void buscarProdutos_deveLancarProductNotFoundException_quandoListaDeProdutosVazia() {
        UUID id = UUID.randomUUID();

        when(produtoRepository.buscarPorId(id)).thenReturn(new ArrayList<>());

        assertThrows(ProductNotFoundException.class, () -> {
            consultarProdutosPorIdUseCase.buscarProdutos(id);
        });
    }
}
