package com.turong.training.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.util.stream.StreamSupport;

@Slf4j
public class PingMeRecordFilter implements RecordFilterStrategy {

    @Override
    public boolean filter(ConsumerRecord consumerRecord) {
        Headers headers = consumerRecord.headers();
        return StreamSupport
                .stream(headers.headers("event-key").spliterator(), false)
                .anyMatch(h -> !StringUtils.equalsIgnoreCase(new String(h.value()), "PING_ME"));
    }

}
