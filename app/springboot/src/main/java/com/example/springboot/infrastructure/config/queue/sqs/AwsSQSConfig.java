package com.example.springboot.infrastructure.config.queue.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;

@Configuration
public class AwsSQSConfig {

  @Value("${cloud.aws.credentials.access-key}")
  private String awsAccessKey;

  @Value("${cloud.aws.credentials.secret-key}")
  private String awsSecretKey;

  @Bean
  public QueueMessagingTemplate queMessagingTemplate() {
    return new QueueMessagingTemplate(amazonSQSAsync());
  }

  @Bean(name ="awsClient")
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withRegion(Regions.SA_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
        .build();
  }
}
