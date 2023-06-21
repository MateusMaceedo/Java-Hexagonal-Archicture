package com.example.springboot.domain.ports.interfaces;

import com.example.springboot.domain.dto.requests.estoque.EstoqueDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface AtualizarEstoqueInterface {
    void atualizarEstoque(String sku, EstoqueDTO estoqueDTO) throws ChangeSetPersister.NotFoundException;
}
