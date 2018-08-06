package com.willi.controller;

import com.rabbitmq.client.ConnectionFactory;
import com.willi.service.impl.CCacheServiceImpl;
import com.willi.service.impl.CheckNumServiceImpl;
import com.willi.service.impl.FindServiceImpl;
import com.willi.service.opera_Record.setting.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
public class SendCodeController {

    @Autowired
    private FindServiceImpl findService;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private CCacheServiceImpl cacheService;

    @Autowired
    private CheckNumServiceImpl checkNumService;

    //默认admin
    //可以由session得来
    String createdUser = "admin";

    @RequestMapping(name = "sendCode", method = RequestMethod.GET)
    @ResponseBody
    public List<String> sendCode(String acceptedUser) throws IOException, TimeoutException {

        List list = new ArrayList();


        if (findService.findOut(acceptedUser)) {

            try {
                Sender sender = new Sender(connectionFactory, cacheService, checkNumService);
                Thread thread = new Thread(sender);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            list.add(createdUser);
            list.add("发送成功");

            return list;
        } else {
            list.add(createdUser);
            list.add("发送失败");
            return list;
        }
    }


}
