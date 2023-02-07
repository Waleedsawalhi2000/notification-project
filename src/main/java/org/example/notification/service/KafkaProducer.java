package org.example.notification.service;

import lombok.RequiredArgsConstructor;
import org.example.notification.entity.Email;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> producer;


    public Boolean push(final Email email) {
        producer.send("notification", email);
        return true;
    }
}
