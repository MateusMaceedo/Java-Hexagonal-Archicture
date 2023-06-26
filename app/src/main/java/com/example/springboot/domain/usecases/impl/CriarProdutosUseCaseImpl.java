package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import com.example.springboot.domain.ports.interfaces.CriarProdutosInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProdutosUseCaseImpl implements CriarProdutosInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public void criarProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO == null || produtoDTO.getNome() == null) {
            String COD_ERROR = "EX-ERROR";
            throw new ProductNotFoundException(COD_ERROR, "valores do produto não pode ser nulo.");
        }

        Produto produto = new Produto(produtoDTO);
        this.produtoRepository.salvar(produto);
    }
}
