package com.example.orderservice.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${app.kafka.kafkaOrderTopic}")
  private String topicOrderName;

  @Value(value = "${app.kafka.kafkaOrderStatusTopic}")
  private String topicOrderStatusName;

  @Bean
  public NewTopic createTopicOrderName() {
    return TopicBuilder.name(topicOrderName)
            .partitions(2)
            .replicas(2)
            .build();
  }

  @Bean
  public NewTopic createTopicOrderStatusName() {
    return TopicBuilder.name(topicOrderStatusName)
            .partitions(1)
            .replicas(1)
            .build();
  }

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put( AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }
}
