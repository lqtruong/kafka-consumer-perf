package com.turong.training.rest.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turong.training.rest.entity.Ping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PingMessage {

    private String val;
    private String pinId;

    public static Ping toPing(PingMessage message) {
        Ping ping = new Ping();
        ping.setVal(message.getVal());
        ping.setPinId(message.getPinId());
        ping.setCreatedAt(new Date());
        ping.setModifiedAt(new Date());
        return ping;
    }
}
