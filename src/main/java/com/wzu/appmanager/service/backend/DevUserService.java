package com.wzu.appmanager.service.backend;


import com.wzu.appmanager.pojo.DevUser;

public interface DevUserService {
    public DevUser login(String userCode, String userPassword) throws Exception;
}
