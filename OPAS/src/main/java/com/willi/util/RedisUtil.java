package com.willi.util;

import redis.clients.util.Slowlog;

import java.util.List;

public interface RedisUtil {

    String getRedisInfo();

    List<Slowlog> getLogs(long entries);

    long getLogsLen();

    String logEmpty();

    long dbSize();
}
