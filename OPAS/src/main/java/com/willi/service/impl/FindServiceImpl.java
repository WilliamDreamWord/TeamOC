package com.willi.service.impl;

import com.willi.repository.UserRepository;
import com.willi.service.opera_Record.FindAccepterService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindServiceImpl implements FindAccepterService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean findOut(String userName) throws HibernateException {

        return userRepository.findAcceptedUser(userName);
    }
}
