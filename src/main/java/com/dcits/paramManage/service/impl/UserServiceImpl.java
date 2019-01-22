package com.dcits.paramManage.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.paramManage.common.CustomException;
import com.dcits.paramManage.entity.User;
import com.dcits.paramManage.mapper.UserMapper;
import com.dcits.paramManage.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username, String userpwd)throws Exception{
		User user = userMapper.login(username, userpwd);
		return user;
	}

	@Override
	public String findUserpwdByUsername(String username) throws Exception{
		
		return userMapper.findUserpwdByUsername(username);
	}

	@Override
	public boolean findUserByUsername(String username) throws Exception{
		
		return StringUtils.isNotBlank(userMapper.findUserByUsername(username))?true:false;
	}

}
