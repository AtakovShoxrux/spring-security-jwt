package com.shaxmen.spring_security_project.kafka;

import com.shaxmen.spring_security_project.kafka.dto.AdditionReconciliationDto;

public interface InternalReconciliationService {

  void additionReconciliation(AdditionReconciliationDto dto);
}