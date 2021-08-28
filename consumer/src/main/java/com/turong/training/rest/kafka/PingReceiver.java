package com.turong.training.rest.kafka;

import com.turong.training.rest.entity.Ping;
import com.turong.training.rest.service.ping.PingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PingReceiver {

    @Autowired
    private PingService pingService;

    @KafkaListener(
            topics = "${spring.kafka.topics.ping}",
            groupId = "ping-topic-group",
            containerFactory = "pingMessageKafkaFactory"
    )
    public void receivePing(@Payload List<PingMessage> messages,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        if (CollectionUtils.isEmpty(messages)) {
            log.info("no message in the batch");
            return;
        }
        log.info("receivePing, Thread ID: {}", Thread.currentThread().getId());
        log.info("receivePing, Partitions: {}", partitions);
        log.info("receivePing messages={}", messages);
        // write to db
        List<Ping> pings = messages.stream().map(PingMessage::toPing).collect(Collectors.toList());
        pingService.writeBatch(pings);
    }

    @KafkaListener(
            topics = "${spring.kafka.topics.ping}",
            groupId = "ping-topic-group-with-filter",
            containerFactory = "pingMessageWithRecordFilterKafkaFactory"
    )
    public void receivePingWithCustomHeader(@Payload List<PingMessage> messages,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions) {
        if (CollectionUtils.isEmpty(messages)) {
            log.info("no message in the batch");
            return;
        }
        log.info("receivePing, Thread ID: {}", Thread.currentThread().getId());
        log.info("receivePing, Partitions: {}", partitions);
        log.info("receivePing messages={}", messages);
        // write to db
        List<Ping> pings = messages.stream().map(PingMessage::toPing).collect(Collectors.toList());
        pingService.writeBatch(pings);
    }

}
