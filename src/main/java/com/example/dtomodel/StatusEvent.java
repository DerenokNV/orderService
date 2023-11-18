package com.example.dtomodel;

import lombok.Data;

import java.time.Instant;

@Data
public class StatusEvent {

  private String status;

  private Instant date;
}
