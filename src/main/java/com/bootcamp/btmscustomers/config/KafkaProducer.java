package com.bootcamp.btmscustomers.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendMessage(String topic, byte[] message) {
        kafkaTemplate.send(topic, message);
    }
}
