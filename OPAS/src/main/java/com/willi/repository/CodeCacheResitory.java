package com.willi.repository;

import com.willi.entity.InvateCodeCacheEntity;

public interface CodeCacheResitory {

    boolean add(InvateCodeCacheEntity codeCahcheEntity);

    boolean delete(String createdUser);

    String getCode(String createdUser);
}
