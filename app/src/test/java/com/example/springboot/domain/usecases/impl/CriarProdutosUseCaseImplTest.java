package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.ports.interfaces.CriarProdutosInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class CriarProdutosUseCaseImplTest {

    @Mock
    private ProdutoRepository produtoRepository;
    @Captor
    private ArgumentCaptor<Produto> produtoCaptor;
    private CriarProdutosInterface criarProdutosInterface;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criarProdutosInterface = new CriarProdutosUseCaseImpl(produtoRepository);
    }

    @Test
    void criarProduto_deveAtualizarEstoqueDoProduto() throws ChangeSetPersister.NotFoundException {

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");

        criarProdutosInterface.criarProduto(produtoDTO);

        verify(produtoRepository).salvar(produtoCaptor.capture());
        Produto produtoCriado = produtoCaptor.getValue();

        assertNotNull(produtoCriado);
        assertEquals(produtoDTO.getNome(), produtoCriado.getNome());
    }

    @Test
    void criarProduto_deveLancarProductNotFoundException_quandoProdutoNulo() {
        assertThrows(ProductNotFoundException.class, () -> {
            criarProdutosInterface.criarProduto(null);
        });

        verifyNoInteractions(produtoRepository);
    }

    @Test
    void criarProduto_deveLancarProductNotFoundException_quandoNomeProdutoNulo() {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        assertThrows(ProductNotFoundException.class, () -> {
            criarProdutosInterface.criarProduto(produtoDTO);
        });

        verifyNoInteractions(produtoRepository);
    }
}
