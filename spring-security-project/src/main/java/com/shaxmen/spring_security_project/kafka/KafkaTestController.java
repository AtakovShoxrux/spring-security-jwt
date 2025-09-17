package com.shaxmen.spring_security_project.kafka;

import com.shaxmen.spring_security_project.kafka.dto.AdditionReconciliationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaMessageProducer producer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody AdditionReconciliationDto dto) {
        producer.sendAdditionMessage(dto);
        return "Message sent!";
    }
}
