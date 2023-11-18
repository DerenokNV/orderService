package com.example.orderservice.listener;

import com.example.dtomodel.OrderEvent;
import com.example.dtomodel.StatusEvent;
import com.example.orderservice.service.KafkaOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaOrderServiceListener {

  private final KafkaOrderService kafkaOrderService;

 /* @KafkaListener( topics = "${app.kafka.kafkaOrderTopic}",
                  groupId = "${app.kafka.kafkaOrderGroupId}",
                  containerFactory = "kafkaOrderConcurrentKafkaListenerContainerFactory"
  )*/
  public void listen( @Payload OrderEvent message,
                      @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                      @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
  ) {
    log.info( "ORDER_SERVICE 11111 CONSUMER" );
    log.info( "Received message: {}", message );
    log.info( "Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, topic, partition, timestamp );

    kafkaOrderService.add( message );
  }

  @KafkaListener( topics = "${app.kafka.kafkaOrderStatusTopic}",
                  groupId = "${app.kafka.kafkaOrderGroupId}",
                  containerFactory = "kafkaOrderStatusConcurrentKafkaListenerContainerFactory"
  )
  public void listenStatus( @Payload StatusEvent message,
                            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
  ) {
    log.info( "ORDER_STATUS_SERVICE 11111 CONSUMER" );
    log.info( "Received message: {}", message );
    log.info( "Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, topic, partition, timestamp );
  }

}
