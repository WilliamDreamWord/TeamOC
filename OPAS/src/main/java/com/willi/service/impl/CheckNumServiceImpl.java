package com.willi.service.impl;

import com.sun.xml.internal.ws.handler.HandlerException;
import com.willi.repository.impl.CodeResitoryImpl;
import com.willi.service.opera_Record.CheckNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckNumServiceImpl implements CheckNumService {

    @Autowired
    private CodeResitoryImpl codeResitory;

    @Override
    public int getNumber(String code) throws HandlerException {

        return codeResitory.getNum(code);
    }
}
