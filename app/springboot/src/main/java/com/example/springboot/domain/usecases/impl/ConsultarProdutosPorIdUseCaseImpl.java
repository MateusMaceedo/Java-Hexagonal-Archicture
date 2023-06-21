package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import com.example.springboot.domain.ports.interfaces.ConsultarProdutosPorIdInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultarProdutosPorIdUseCaseImpl implements ConsultarProdutosPorIdInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> buscarProdutos(UUID id) {
        List<Produto> produtos = this.produtoRepository.buscarPorId(id);

        if (produtos.isEmpty()) {
            throw new ProductNotFoundException("Erro ao realizar a operação", "EX-ERROR");
        }

        return produtos.stream()
                .map(Produto::toProdutoDTO)
                .collect(Collectors.toList());
    }
}
