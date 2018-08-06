package com.willi.controller;

import com.willi.entity.RecordLogEntity;
import com.willi.service.impl.RecordLogServiceImpl;
import com.willi.service.login.LoginService;
import com.willi.util.DateUtil;
import com.willi.util.impl.DateUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author william
 * @Date 2018/5/22
 * 用户通过controller完成登录，登录成功的用户系统会通过session保护基础信息（用户名），方便用户在一段时间内的重新登录
 */
@Controller
public class LoginController {

    private final static String FIRST_LOG = "您成功登录系统";

    @Autowired
    private LoginService loginService1;

    @Autowired
    private RecordLogServiceImpl recordLogService;

    @Autowired
    private DateUtilImpl dateUtil;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public List<String> doPost(HttpServletRequest request, HttpServletResponse response, String userName, String userPwd, String checkCode) throws ServletException, IOException {

        List<String> list = new ArrayList<>();

        request.setAttribute("userName", userName);
        request.setAttribute("userPwd", userPwd);
        request.setAttribute("checkCode", checkCode);

        if (doGet(request, response)) {
            list.add(userName);
            list.add(userPwd);
            list.add(checkCode);

            return list;
        } else {
            return null;
        }
    }

    public boolean doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名，密码，验证码
        request.setCharacterEncoding("utf-8");
        String userName = (String) request.getAttribute("userName");
        String userPwd = (String) request.getAttribute("userPwd");
        String checkCode = (String) request.getAttribute("checkCode");

        RecordLogEntity recordLogEntity = new RecordLogEntity();

        //验证request传参正确性
/*        System.out.println(userName);
        System.out.println(userPwd);*/

        //验证验证码
/*
        String checkCode_session = (String) request.getSession().getAttribute("checkCode_session");
        request.getSession().removeAttribute("checkCode_session");

        if(loginService1.judgeCode(checkCode_session, checkCode)) {
            //验证码输入错误
            request.setAttribute("msg", "验证码输入错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
*/

        //验证用户名和密码
        if (loginService1.judgeUser(userName, userPwd)) {

            //登录成功
            //将登录信息保存在session中
            request.setAttribute("userName", userName);

            String loginDate = dateUtil.getDate();
            String loginLog = userName + " " + FIRST_LOG + " " + loginDate;

            recordLogEntity.setCreatedDate(loginDate);
            recordLogEntity.setRecored(loginLog);

            if (recordLogService.addLog(recordLogEntity)) {
                System.out.println("记录 - 登录 - 日志成功");
            } else {
                System.out.println("记录日志失败");
            }

            return true;
/*
            response.sendRedirect("welcome.jsp");
*/
        } else {
            //登录失败
            request.setAttribute("msg", "登录失败！");
            return false;
/*
            request.getRequestDispatcher("login.jsp").forward(request, response);
*/
        }
    }
}

