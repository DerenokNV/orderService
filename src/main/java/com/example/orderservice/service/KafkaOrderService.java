package com.example.orderservice.service;

import com.example.dtomodel.OrderEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaOrderService {

  private final List<OrderEvent> messages = new ArrayList<>();

  public void add( OrderEvent orderEvent ) {
    messages.add( orderEvent );
  }
}
