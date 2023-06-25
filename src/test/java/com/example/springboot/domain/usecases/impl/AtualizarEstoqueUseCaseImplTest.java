package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.ports.interfaces.AtualizarEstoqueInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

public class AtualizarEstoqueUseCaseImplTest {

    @Mock
    private ProdutoRepository produtoRepository;
    private AtualizarEstoqueInterface atualizarEstoqueUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        atualizarEstoqueUseCase = new AtualizarEstoqueUseCaseImpl(produtoRepository);
    }

    @Test
    void atualizarEstoque_deveAtualizarEstoqueDoProduto() throws ChangeSetPersister.NotFoundException {

        String sku = "SKU123";
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setQuantidade(10);

        Produto produto = mock(Produto.class); // Mock do objeto Produto
        when(produtoRepository.buscarPeloSku(sku)).thenReturn(produto);

        atualizarEstoqueUseCase.atualizarEstoque(sku, estoqueDTO);

        verify(produtoRepository).buscarPeloSku(sku);
        verify(produto).atualizarEstoque(estoqueDTO.getQuantidade());
        verify(produtoRepository).salvar(produto);
    }

    @Test
    void atualizarEstoque_deveLancarNotFoundException_quandoProdutoNaoExistir() {
        String sku = "SKU123";
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setQuantidade(10);

        when(produtoRepository.buscarPeloSku(sku)).thenReturn(null);

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            atualizarEstoqueUseCase.atualizarEstoque(sku, estoqueDTO);
        });

        verify(produtoRepository).buscarPeloSku(sku);
        verifyNoMoreInteractions(produtoRepository);
    }
}
