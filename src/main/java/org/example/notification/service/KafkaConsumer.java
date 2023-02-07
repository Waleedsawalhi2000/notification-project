package org.example.notification.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.notification.entity.Email;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final Gson gson;

    @KafkaListener(topics = "notification",
            groupId = "notification",
            concurrency = "1")
    public void listen(final String object) {
        final Email email = gson.fromJson(object, Email.class);
    }
}
