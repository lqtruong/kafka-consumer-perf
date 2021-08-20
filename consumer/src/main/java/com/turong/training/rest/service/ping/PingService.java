package com.turong.training.rest.service.ping;

import com.turong.training.rest.entity.Ping;

import java.util.List;

public interface PingService {

    void writeBatch(List<Ping> pings);


}
