package com.wzu.appmanager.service.developer;


import com.wzu.appmanager.dao.DevUserMapper;
import com.wzu.appmanager.pojo.DevUser;
import com.wzu.appmanager.util.CodeUtil;
import com.wzu.appmanager.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	private DevUserMapper devUserMapper;
	@Override
	public DevUser login(String devCode, String devPassword) throws Exception {
		// TODO Auto-generated method stub
		DevUser user = null;
		user = devUserMapper.getLoginUser(devCode);
		//匹配密码
		if(null != user){
			if(!user.getDevpassword().equals(devPassword))
				user = null;
		}
		return user;
	}
	@Override
	public boolean doRegister(String userCode, String password, String email,String devname,String devinfo) {
		// 这里可以验证各字段是否为空
		if(userCode==null||password==null||email==null)
			return false;
		//利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
		if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
			return false;
		}
		//生成激活码
		String code= CodeUtil.generateUniqueCode();
		DevUser user=new DevUser(userCode,password,email,0,code,devname,devinfo);
		//将用户保存到数据库
//		UserDao userDao=new UserDaoImpl();

		//保存成功则通过线程的方式给用户发送一封邮件
		if(devUserMapper.insert(user)>0){
			new Thread(new MailUtil(email, code)).start();;
			return true;
		}
		return false;
	}

	@Override
	public boolean activeUser(String code) {
		if(devUserMapper.activeUser(code)>0){
			return true;
		}else{
			return false;
		}
	}
}
