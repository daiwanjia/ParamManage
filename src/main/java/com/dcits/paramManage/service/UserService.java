package com.dcits.paramManage.service;

import com.dcits.paramManage.entity.User;

public interface UserService {
	User login(String username,String userpwd)throws Exception;
	 /**
     * 查用户名对应的密码
     * @param username
     * @return  userpwd
     */
    String findUserpwdByUsername(String username)throws Exception;
    /**
     * 查用户是否存在
     * @param username
     * @return userid
     */
    boolean findUserByUsername(String username)throws Exception;
}
