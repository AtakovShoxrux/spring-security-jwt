package com.shaxmen.spring_security_project.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionReconciliationDto {

  private String transactionId;
  private String accountId;
  private Double amount;
  private String description;
}