package com.example.springboot.domain.usecases.impl;

import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import com.example.springboot.domain.ports.interfaces.AtualizarEstoqueInterface;
import com.example.springboot.infrastructure.config.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AtualizarEstoqueUseCaseImpl implements AtualizarEstoqueInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public void atualizarEstoque(String sku, EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException {
        Produto produto = this.produtoRepository.buscarPeloSku(sku);

        if (Objects.isNull(produto))
            throw new ChangeSetPersister.NotFoundException();

        produto.atualizarEstoque(estoqueDTO.getQuantidade());

        this.produtoRepository.salvar(produto);
    }
}
