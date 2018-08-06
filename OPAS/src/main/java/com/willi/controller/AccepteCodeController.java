package com.willi.controller;

import com.rabbitmq.client.ConnectionFactory;
import com.sun.xml.internal.ws.handler.HandlerException;
import com.willi.service.impl.CodeServiceImpl;
import com.willi.service.opera_Record.setting.Receiver;
import com.willi.service.opera_Record.setting.Receiver1;
import com.willi.util.impl.DateUtilImpl;
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
public class AccepteCodeController {


    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private CodeServiceImpl codeService;

    @Autowired
    private DateUtilImpl dateUtil;

    /**
     * 用户收到邀请码，并且点击邀请码，才能触发该Controller
     */

    @RequestMapping(value = "acceptCode", method = RequestMethod.GET)
    @ResponseBody
    public List<String> accepteCode(boolean click) throws IOException, TimeoutException {

        //接受者username
        //接受者用户登录后即可获取
        String acceptedUser = "william";

        List<String> list = new ArrayList<>();

        list.add(acceptedUser);

        if (click) {
            try {
                Receiver receiver = new Receiver(connectionFactory, dateUtil, codeService);
                Thread thread = new Thread(receiver);
                thread.start();


            } catch (HandlerException e) {
                e.printStackTrace();

                list.add("邀请码接收失败");

            }
        } else {
            list.add("用户没有点击，此邀请码将会作废");

            try {
                Receiver1 receiver1 = new Receiver1(connectionFactory);
                Thread thread = new Thread(receiver1);
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }

        list.add("邀请码接收成功。");
        return list;

    }
}
