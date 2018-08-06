package com.willi.util.impl;

import com.willi.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import java.util.List;

public class RedisUtilImpl implements RedisUtil {

    /**
     * 实现工具类接口，方便开放人员查看redis信息
     */

    @Autowired
    JedisPool jedisPool;

    // 获取redis 服务器信息
    @Override
    public String getRedisInfo() {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            Client client = jedis.getClient();
            client.info();
            String info = client.getBulkReply();
            return info;
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }

    // 获取日志列表
    @Override
    public List<Slowlog> getLogs(long entries) {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            List<Slowlog> logList = jedis.slowlogGet(entries);
            return logList;
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }

    // 获取日志条数
    @Override
    public long getLogsLen() {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            long logLen = jedis.slowlogLen();
            return logLen;
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }

    // 清空日志
    @Override
    public String logEmpty() {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            return jedis.slowlogReset();
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }

    // 获取占用内存大小
    @Override
    public long dbSize() {

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            // TODO 配置redis服务信息
            Client client = jedis.getClient();
            client.dbSize();
            return client.getIntegerReply();
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }
}
