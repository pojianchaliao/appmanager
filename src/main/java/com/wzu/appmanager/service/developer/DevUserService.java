package com.wzu.appmanager.service.developer;


import com.wzu.appmanager.pojo.DevUser;

public interface DevUserService {
	/**
	 * 用户登录
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	public DevUser login(String devCode, String devPassword) throws Exception;

	boolean doRegister(String userCode, String password, String email,String userName ,String devinfo);

	boolean activeUser(String code);
}
