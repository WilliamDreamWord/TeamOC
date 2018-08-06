package com.willi.controller;

import com.willi.service.impl.CCacheServiceImpl;
import com.willi.service.impl.CodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CancelSendController {

    @Autowired
    private CCacheServiceImpl cacheService;

    @Autowired
    private CodeServiceImpl codeService;

    @RequestMapping(value = "cancelSend", method = RequestMethod.GET)
    @ResponseBody
    public List<String> Cancel(boolean cancel) {

        String userName = "admin";


        List<String> list = new ArrayList<>();

        if (cancel) {
            if (codeService.deleteCode(userName) && cacheService.deleteCache(userName)) {
                list.add("已清除数据和缓存");
                return list;
            }
        }

        list.add("数据及缓存清除失败");
        return list;

    }
}
