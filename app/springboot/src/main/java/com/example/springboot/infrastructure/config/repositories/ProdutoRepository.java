package com.example.springboot.infrastructure.config.repositories;

import com.example.springboot.domain.mapper.Produto;
import com.example.springboot.domain.usecases.interfaces.ProdutoRepositoryPort;
import com.example.springboot.infrastructure.adapters.entities.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoRepository implements ProdutoRepositoryPort {

    private final SpringProdutoRepository springProdutoRepository;

    @Override
    public List<Produto> buscarPorId(UUID id) {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findAllById(Collections.singleton(id));
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarTodos() {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findAll();
        return produtoEntities.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto buscarPeloSku(String sku) {
        Optional<ProdutoEntity> produtoEntity = this.springProdutoRepository.findBySku(sku);

        if (produtoEntity.isPresent())
            return produtoEntity.get().toProduto();

        throw new RuntimeException("Produto não existe");
    }

    @Override
    public void salvar(Produto produto) {
        ProdutoEntity produtoEntity;
        if (Objects.isNull(produto.getCodigo()))
            produtoEntity = new ProdutoEntity(produto);
        else {
            produtoEntity = this.springProdutoRepository.findById(produto.getCodigo()).get();
            produtoEntity.atualizar(produto);
        }

        this.springProdutoRepository.save(produtoEntity);
    }
}
