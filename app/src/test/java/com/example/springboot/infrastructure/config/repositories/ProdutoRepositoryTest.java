package com.example.springboot.infrastructure.config.repositories;

import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.infrastructure.adapters.entities.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoRepositoryTest {

    private ProdutoRepository produtoRepository;
    @Mock
    private SpringProdutoRepository springProdutoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoRepository = new ProdutoRepository(springProdutoRepository);
    }

    @Test
    void buscarPorId_DeveRetornarListaDeProdutosCorreta() {


    }
}
