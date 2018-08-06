package com.willi.service.impl;

import com.willi.entity.RecordLogEntity;
import com.willi.repository.impl.RecordRepositoryImpl;
import com.willi.service.redis.RecordLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordLogServiceImpl implements RecordLogService {

    @Autowired
    RecordRepositoryImpl recordRepository;

    @Override
    public boolean addLog(RecordLogEntity recordLogEntity) {

        return recordRepository.save(recordLogEntity);
    }

    @Override
    public List<String> checkLog() {

        return recordRepository.look();
    }
}
