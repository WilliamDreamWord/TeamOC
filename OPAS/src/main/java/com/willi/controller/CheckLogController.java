package com.willi.controller;

import com.willi.service.impl.RecordLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckLogController {

    @Autowired
    RecordLogServiceImpl recordLogService;

    @RequestMapping(value = "checkLog", method = RequestMethod.GET)
    @ResponseBody
    public List<String> checlLog() {

        return recordLogService.checkLog();
    }
}
