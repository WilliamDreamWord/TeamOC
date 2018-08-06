package com.willi.controller;

import com.willi.entity.InvateCodeCacheEntity;
import com.willi.entity.RecordLogEntity;
import com.willi.entity.UserInvateCodeEntity;
import com.willi.entity.UserRoomEntity;
import com.willi.service.impl.CCacheServiceImpl;
import com.willi.service.impl.CodeServiceImpl;
import com.willi.service.impl.RecordLogServiceImpl;
import com.willi.service.impl.RoomServiceImpl;
import com.willi.service.redis.RecordLogService;
import com.willi.util.impl.DateUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateCodeController {

    /**
     * 用户创建邀请码
     */

    private final static String SECOND_LOG = "您成功产生了邀请码";
    public final static String THIRD_LOG = "加入了该小组";
    public final static String FOURTH_LOG = "向您发送了一封邀请码";
    public final static String FIFTH_LOG = "发送了一封邀请码";
    public final static String SIXTH_LOG = "退出了该小组";
    public final static String SEVENTH_LOG = "您成功加入";
    public final static String EIGHTH_LOG = "成功加入您的小组";
    public final static String NINTH_LOG = "您成功创建了";
    public final static String TENTH_LOG = "退出了您的小组";

    @Autowired
    private DateUtilImpl dateUtil;

    @Autowired
    private CodeServiceImpl codeService;

    @Autowired
    private CCacheServiceImpl cacheService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private RecordLogServiceImpl recordLogService;

    //版本号唯一标识，表示邀请码一旦创建后，版本号为1；邀请码一旦被接受，写入接受者信息时，从顺位接受者开始写入数据
    public static final int FIRST_VERSION = 1;

    @RequestMapping(value = "createCode", method = RequestMethod.GET)
    @ResponseBody
    public List<String> createCode(String room) {

        UserInvateCodeEntity codeEntity = new UserInvateCodeEntity();
        InvateCodeCacheEntity cahcheEntity = new InvateCodeCacheEntity();
        UserRoomEntity roomEntity = new UserRoomEntity();
        RecordLogEntity recordLogEntity = new RecordLogEntity();

        List<String> list = new ArrayList<>();

        String userName = "admin";
        String createdDate = dateUtil.getDate();
        String code = "http://localhost:3000#" + " " + createdDate + " &" + room;
        String createdCodelog = userName + " " + SECOND_LOG + " " + createdDate;

        codeEntity.setCreatedUser(userName);
        codeEntity.setCreatedDate(createdDate);
        codeEntity.setNumVersion(FIRST_VERSION);
        codeEntity.setCode(code);

        cahcheEntity.setCreatedUser(userName);
        cahcheEntity.setCode(code);

//        roomEntity.setId(FIRST_VERSION);
        roomEntity.setRoomName(room);
        roomEntity.setCreatedUser(userName);
        roomEntity.setCreatedDate(createdDate);

        recordLogEntity.setCreatedDate(createdDate);
        recordLogEntity.setRecored(createdCodelog);

        list.add(code);

        if (codeService.createCode(codeEntity) && cacheService.addCache(cahcheEntity) && roomService.addRoom(roomEntity)) {

            if (recordLogService.addLog(recordLogEntity)) {
                System.out.println("记录 - 创建邀请码 - 日志成功");
            } else {
                System.out.println("记录日志失败");
            }
            list.add(userName);
            list.add(createdDate);
            list.add("邀请码成功存入数据库");
            list.add("邀请码信息已缓存");
            list.add("房间信息已成功存入数据库");
            return list;
        }

        return list;
    }
}
