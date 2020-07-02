package com.wzu.appmanager.service.backend;


import com.wzu.appmanager.dao.DevUserMapper;
import com.wzu.appmanager.pojo.BackendUser;
import com.wzu.appmanager.pojo.DevUser;
import org.springframework.beans.factory.annotation.Autowired;

public class DevUserServiceImpl implements DevUserService{
    @Autowired
    private DevUserMapper mapper;
    @Override 
    public DevUser login(String userCode, String userPassword) throws Exception {
        DevUser user = null;
        user = mapper.getLoginUser(userCode);
        //匹配密码
        if(null != user){
            if(!user.getDevpassword().equals(userPassword))
                user = null;
        }
        return user;
    }
}
