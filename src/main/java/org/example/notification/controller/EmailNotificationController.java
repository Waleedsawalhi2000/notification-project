package org.example.notification.controller;

import lombok.RequiredArgsConstructor;
import org.example.notification.entity.Email;
import org.example.notification.service.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email/notification")
@RequiredArgsConstructor
public class EmailNotificationController {
    private final KafkaProducer producer;



    @PostMapping
    public Boolean produce(@RequestBody final Email email) {
        return producer.push(email);
    }
}
