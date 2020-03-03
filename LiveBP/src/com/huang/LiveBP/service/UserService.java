package com.huang.LiveBP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.LiveBP.mapper.UserMapper;
import com.huang.LiveBP.pojo.UserPojo;

@Service("userService")
public class UserService implements UserServiceImpl{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("测试...");
	}
	
	//注册
	@Override
	public int addUser(UserPojo userPojo) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(userPojo);
	}
	
	//判断手机号是否已注册
	@Override
	public UserPojo regUserphoneIfSame(String userphone) {
		// TODO Auto-generated method stub
		UserPojo regUserphoneIfSame = userMapper.regUserphoneIfSame(userphone);
		return regUserphoneIfSame;
	}
	
	//判断昵称是否存在
	@Override
	public UserPojo regUsernameIfSame(String username) {
		// TODO Auto-generated method stub
		UserPojo regUsernameIfSame = userMapper.regUsernameIfSame(username);
		return regUsernameIfSame;
	}
	
	//登录
	@Override
	public UserPojo selectUser(String userphone,String userpwd) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(userphone,userpwd);
	}
	
	//查询用户by昵称
	@Override
	public List<UserPojo> selectUserByUsername(String username,String userphone) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUsername(username,userphone);
	}
	
	//查询用户的关注量by手机号
	public int selectUserFollowByUp(String userphone) {
		return userMapper.selectUserFollowByUp(userphone);
	}
	
	//是否关注了by自身用户和被查用户
	@Override
	public int selIfFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		return userMapper.selIfFolByUpAndFp(userphone, followphone);
	}
	
	//查询用户的粉丝量by手机号
	@Override
	public int selectUserFansByUp(String userphone) {
		// TODO Auto-generated method stub
		return userMapper.selectUserFansByUp(userphone);
	}
	
	//取消关注
	@Override
	public void delFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		userMapper.delFolByUpAndFp(userphone, followphone);
	}
	
	//取消粉丝
	@Override
	public void delFansByUpAndFp(String userphone, String fansphone) {
		// TODO Auto-generated method stub
		userMapper.delFansByUpAndFp(userphone, fansphone);
	}
	
	//关注ta
	@Override
	public void addFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		userMapper.addFolByUpAndFp(userphone, followphone);
	}
	
	//添加粉丝 
	@Override
	public void addFansByUpAndFp(String userphone, String fansphone) {
		// TODO Auto-generated method stub
		userMapper.addFansByUpAndFp(userphone, fansphone);
	}

	@Override
	public void updateUsername(String username, String userphone) {
		// TODO Auto-generated method stub
		userMapper.updateUsername(username, userphone);
	}

	@Override
	public void upTouxiang(String fileName, String userphone) {
		// TODO Auto-generated method stub
		userMapper.upTouxiang(fileName, userphone);
	}

	@Override
	public int addTouxiang(String userphone, String fileName) {
		// TODO Auto-generated method stub
		return userMapper.addTouxiang(userphone, fileName);
	}

	@Override
	public String findTouxiang(String userphone) {
		// TODO Auto-generated method stub
		return userMapper.findTouxiang(userphone);
	}

	@Override
	public String findUsertext(String userphone) {
		// TODO Auto-generated method stub
		return userMapper.findUsertext(userphone);
	}

	@Override
	public void updateUsertext(String usertext, String userphone) {
		// TODO Auto-generated method stub
		userMapper.updateUsertext(usertext, userphone);
	}

	@Override
	public UserPojo session(String userphone) {
		// TODO Auto-generated method stub
		return userMapper.session(userphone);
	}

	@Override
	public void updateUseremail(String useremail, String userphone) {
		// TODO Auto-generated method stub
		userMapper.updateUseremail(useremail, userphone);
	}

	@Override
	public void updateUserpwd(String userpwd, String userphone) {
		// TODO Auto-generated method stub
		userMapper.updateUserpwd(userpwd, userphone);
	}
	

	
	
	
	

}
