package com.willi.service.opera_Record.setting;

import com.rabbitmq.client.*;
import com.willi.service.impl.CodeServiceImpl;
import com.willi.util.impl.DateUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

@Service
public class Receiver implements Runnable {

    /**
     * 接收者点击进入房间后，即实现接收线程，将接收者信息存入数据库
     */

    private static String queueName = "william";

    private final DateUtilImpl dateUtil;

    private final CodeServiceImpl codeService;

    private final ConnectionFactory connectionFactory;

    @Autowired
    public Receiver(ConnectionFactory connectionFactory, DateUtilImpl dateUtil, CodeServiceImpl codeService) {
        this.connectionFactory = connectionFactory;
        this.dateUtil = dateUtil;
        this.codeService = codeService;

    }


    @Override
    public void run() {

        boolean autoAck = true;
        try {

            String acceptedDate = dateUtil.getDate();

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

                //开始将消费者信息存入数据库
                codeService.acceptCode(queueName, acceptedDate, message);
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
