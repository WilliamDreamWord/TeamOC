package com.willi.repository.impl;

import com.willi.entity.RecordLogEntity;
import com.willi.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class RecordRepositoryImpl implements RecordRepository {

    @Autowired
    private Jedis jedis;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean save(RecordLogEntity recordLogEntity) {

        //利用了StringRdisTemplate的特性 通过绑定的方式
/*        BoundValueOperations<String, String> bound = redisTemplate.boundValueOps(recordLogEntity.getCreatedDate());
        bound.set(recordLogEntity.getRecord());

        //追加，和StringBuilder的append一样功能
        bound.append(recordLogEntity.getRecord());*/

        boolean result = false;

        final String key = recordLogEntity.getCreatedDate();
        String value = recordLogEntity.getRecord();

        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(String key) {

        try {
            redisTemplate.delete(key);
            System.out.println(key + " 删除成功。");
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public boolean update(RecordLogEntity recordLogEntity) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        if (ops.get(recordLogEntity.getCreatedDate()) != null) {
            ops.set(recordLogEntity.getCreatedDate(), recordLogEntity.getRecord());
            return true;
        }

        return false;
    }

    @Override
    public List<String> look() {

        List<String> list = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        // 获取所有key
        Set<byte[]> keySet = jedis.keys("*".getBytes());
        byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
        // 获取所有value
        byte[][] values = jedis.mget(keys).toArray(new byte[keySet.size()][]);

        for (int i = 0; i < keySet.size(); ++i) {

            list.add(new String(values[i]));
        }

        long endTime = System.currentTimeMillis();

        jedis.close();


        System.out.println("耗时：" + (endTime - startTime));

        return list;

    }



/*    private static String byte2hx(byte[] buffer) {
        String h = "0x";

        for (byte aBuffer : buffer) {
            String temp = Integer.toHexString(aBuffer & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + " " + temp;
        }

        return h;

    }*/
}
