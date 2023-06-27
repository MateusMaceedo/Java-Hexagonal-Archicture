package com.example.springboot.application.adapters.api.rest.sqs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
@AllArgsConstructor
public class CartaoCreditoResource {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.fila.compra_cartao_credito}")
    private String uriCompraCartaoCredito;

    @PostMapping
    public ResponseEntity<String> consultaCartaoCredito(@RequestBody String mensagem){
        queueMessagingTemplate.send(uriCompraCartaoCredito, MessageBuilder.withPayload(mensagem).build());
        return ResponseEntity.ok("Solicitação enviada com sucesso.");
    }
}
