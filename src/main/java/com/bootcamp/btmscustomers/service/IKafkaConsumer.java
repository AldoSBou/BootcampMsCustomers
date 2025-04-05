package com.bootcamp.btmscustomers.service;

public interface IKafkaConsumer {
    void consumeUserCreated(byte[] message);
}
