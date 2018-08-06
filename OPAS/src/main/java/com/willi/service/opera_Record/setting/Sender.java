package com.willi.service.opera_Record.setting;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.willi.service.impl.CCacheServiceImpl;
import com.willi.service.impl.CheckNumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class Sender implements Runnable {

    private static String queueName = "william";

    private String createdUser = "admin";

    private CCacheServiceImpl cacheService;

    private final ConnectionFactory connectionFactory;

    private CheckNumServiceImpl checkNumService;

    @Autowired
    public Sender(ConnectionFactory connectionFactory, CCacheServiceImpl cacheService, CheckNumServiceImpl checkNumService) {
        this.connectionFactory = connectionFactory;
        this.cacheService = cacheService;
        this.checkNumService = checkNumService;
    }


    @Override
    public void run() {

        String message = cacheService.getCache(createdUser);

        if (checkNumService.getNumber(message) != 5) {

            try {

                connectionFactory.setHost("127.0.0.1");
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(queueName, false, false, false, null);

                channel.basicPublish("", queueName, null, message.getBytes());

                channel.close();
                connection.close();

            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (checkNumService.getNumber(message) == 0) {

            System.out.println("房间创建失败，请重新创建");
        }

    }
}
