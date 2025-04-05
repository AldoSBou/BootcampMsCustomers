package com.bootcamp.btmscustomers.service.impl;

import com.bootcamp.btmscustomers.model.Customer;
import com.bootcamp.btmscustomers.model.kafka.CustomerKafka;
import com.bootcamp.btmscustomers.service.ICustomerService;
import com.bootcamp.btmscustomers.service.IKafkaConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService implements IKafkaConsumer {

    private final ICustomerService customerService;
    private final ObjectMapper objectMapper;


    @Override
    @KafkaListener(topics = "${bootcamp.kafka.topic.userCreated:user-created}", groupId = "${bootcamp.kafka.consumer.group:customer-group}")
    public void consumeUserCreated(byte[] message) {
        log.info("Mensaje recibido en bytes: {}", message);

        try {
            CustomerKafka customerKafka = objectMapper.readValue(message, CustomerKafka.class);
            log.info("Mensaje deserializado: {}", customerKafka);

            Customer customer = new Customer();
            customer.setName(customerKafka.getName());
            customer.setEmail(customerKafka.getEmail());
            customer.setDocumentNumber(String.valueOf(customerKafka.getDocumentNumber()));
            customer.setLastName(customerKafka.getLastName());

            customerService.save(customer).doOnNext(System.out::println).subscribe();

        } catch (IOException e) {
            log.error("Error al deserializar el mensaje: {}", e.getMessage());
        }
    }

}
