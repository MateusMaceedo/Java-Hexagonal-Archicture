package com.example.springboot.infrastructure.config.queue.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

public interface AwsSQSListener {
    QueueMessagingTemplate queueMessagingTemplate();
    AmazonSQSAsync amazonSQSAsync();
}
