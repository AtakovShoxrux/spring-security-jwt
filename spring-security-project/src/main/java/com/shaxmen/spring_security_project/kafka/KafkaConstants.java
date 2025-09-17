package com.shaxmen.spring_security_project.kafka;

public class KafkaConstants {

  // Existing factories
  public static final String KAFKA_LISTENER_CONTAINER_FACTORY = "paymentKafkaListenerContainerFactory";
  public static final String COMMON_LISTENER_CONTAINER_FACTORY = "commonKafkaListenerContainerFactory";

  // Additions for Addition Reconciliation
  public static final String ADDITION_RECONCILIATION_TOPIC = "addition-reconciliation-topic";
  public static final String ADDITION_RECONCILIATION_GROUP_ID = "addition-reconciliation-group";
}
