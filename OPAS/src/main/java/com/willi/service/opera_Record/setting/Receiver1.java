package com.willi.service.opera_Record.setting;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class Receiver1 implements Runnable {

    /**
     * 接收者点击进入房间后，即实现接收线程，将接收者信息存入数据库
     */

    private static String queueName = "william";


    private final ConnectionFactory connectionFactory;

    @Autowired
    public Receiver1(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

    }


    @Override
    public void run() {

        boolean autoAck = true;
        try {

            connectionFactory.setHost("127.0.0.1");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            QueueingConsumer consumer = new QueueingConsumer(channel);

            channel.basicConsume(queueName, autoAck, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("Received: " + message);
                System.out.println(message);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
