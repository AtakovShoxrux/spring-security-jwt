package com.shaxmen.spring_security_project.kafka;

import com.shaxmen.spring_security_project.kafka.dto.AdditionReconciliationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InternalReconciliationServiceImpl implements InternalReconciliationService {

  @Override
  public void additionReconciliation(AdditionReconciliationDto dto) {
    // TODO: implement your business logic here
    log.info("Processing addition reconciliation: {}", dto);
  }
}