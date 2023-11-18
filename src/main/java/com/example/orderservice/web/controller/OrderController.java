package com.example.orderservice.web.controller;

import com.example.dtomodel.OrderEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.KafkaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class OrderController {

  @Value( "${app.kafka.kafkaOrderTopic}" )
  private String topicName;

  private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

  private final KafkaOrderService kafkaOrderService;

  @PostMapping("/send")
  public ResponseEntity<String> sendMessage( @RequestBody Order order ) {
    OrderEvent event = new OrderEvent( order.getProduct(), order.getQuantity() );
    kafkaTemplate.send( topicName, event );

    return ResponseEntity.ok( "Message sent to kafka" );
  }

}
