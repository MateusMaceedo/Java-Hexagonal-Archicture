package com.example.springboot.infrastructure.adapters.messagebroker.sqs.consumer;

public interface Consumer {
    void recieveMessage(String message) throws Exception;
}
