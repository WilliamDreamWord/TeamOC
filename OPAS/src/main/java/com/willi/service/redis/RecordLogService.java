package com.willi.service.redis;

import com.willi.entity.RecordLogEntity;

import java.util.List;

public interface RecordLogService {

    boolean addLog(RecordLogEntity recordLogEntity);

    List<String> checkLog();
}
