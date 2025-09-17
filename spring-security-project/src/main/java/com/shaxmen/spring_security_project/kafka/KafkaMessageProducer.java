package com.shaxmen.spring_security_project.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer<T> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  public KafkaMessageProducer(KafkaTemplate<String, T> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String tenantId, T message) {
    kafkaTemplate.send(
        MessageBuilder.withPayload(message)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader("tenantId", tenantId)
            .build()
    );
  }
}