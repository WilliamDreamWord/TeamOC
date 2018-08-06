package com.willi.repository;

import com.willi.entity.RecordLogEntity;

import java.util.List;

public interface RecordRepository {

    boolean save(RecordLogEntity recordLogEntity);

    boolean delete(String key);

    boolean update(RecordLogEntity recordLogEntity);

    List<String> look();
}
