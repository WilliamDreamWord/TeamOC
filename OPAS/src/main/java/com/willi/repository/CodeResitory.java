package com.willi.repository;

import com.willi.entity.UserInvateCodeEntity;

import java.util.List;

public interface CodeResitory {

    /**
     * 对Code数据库的操作，只有增加查看的功能，但提供删改接口。
     * 查看功能是管理员权限
     *
     * @param codeEntity
     * @return
     */

    boolean addCode(UserInvateCodeEntity codeEntity);

    boolean updateCode(String acceptedUser, String acceptedDate, String code);

    String getCode(String createdUser);

    int getNum(String code);

    //管理员权限
    List<UserInvateCodeEntity> lookAllCode();

    List<UserInvateCodeEntity> findCode(String createdUser);

    boolean deleteCode(String createdUser);

    //以下接口为日后需求所准备
    boolean deleteAll();


}
