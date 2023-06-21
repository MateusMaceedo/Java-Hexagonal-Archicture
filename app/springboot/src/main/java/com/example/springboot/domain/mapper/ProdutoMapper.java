package com.example.springboot.domain.mapper;

import com.example.springboot.domain.dto.requests.produtos.ProdutoDTO;
import com.example.springboot.infrastructure.adapters.entities.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {

    private final ModelMapper modelMapper;

    public ProdutoEntity toEntity(ProdutoDTO produtoDTO) {
        return modelMapper.map(produtoDTO, ProdutoEntity.class);
    }

    public ProdutoDTO toDTO(ProdutoEntity produtoEntity) {
        return modelMapper.map(produtoEntity, ProdutoDTO.class);
    }

    public ProdutoDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public Produto toProduto(ProdutoDTO produtoDTO) {
        return modelMapper.map(produtoDTO, Produto.class);
    }
}
