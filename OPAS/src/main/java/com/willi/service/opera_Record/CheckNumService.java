package com.willi.service.opera_Record;

import com.sun.xml.internal.ws.handler.HandlerException;

public interface CheckNumService {

    int getNumber(String code) throws HandlerException;
}
