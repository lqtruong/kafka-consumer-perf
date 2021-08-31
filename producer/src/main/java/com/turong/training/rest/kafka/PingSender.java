package com.turong.training.rest.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Slf4j
public class PingSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.topics.ping}")
    private String pingTopic;

    public void send(int times, String val) {

        IntStream.range(0, times).forEach(i -> {
            log.info("Send message ith={}", i);
            PingMessage body = new PingMessage();
            body.setVal(val);
            body.setPinId("" + i);
            Message<PingMessage> message = MessageBuilder
                    .withPayload(body)
                    .setHeader(KafkaHeaders.TOPIC, pingTopic)
                    .build();
            kafkaTemplate.send(message);
        });

    }

    public void sendWithCustomHeader(int times, String val) {

        final int from = times / 10;
        final int to = times / 3;
        IntStream.range(0, times).forEach(i -> {
            log.info("Send message ith={}", i);
            PingMessage body = new PingMessage();
            body.setVal(val);
            body.setPinId("" + i);
            String headerEventKey = "";
            if (i >= from && i <= to) {
                headerEventKey = "PING_ME";
            } else {
                headerEventKey = "USER_PING";
            }
            Message<PingMessage> message = MessageBuilder
                    .withPayload(body)
                    .setHeader(KafkaHeaders.TOPIC, pingTopic)
                    .setHeader("event-key", headerEventKey)
                    .build();
            kafkaTemplate.send(message);
        });

    }

}
