package com.huang.LiveBP.service;

import java.util.List;

import com.huang.LiveBP.pojo.UserPojo;

public interface UserServiceImpl {
	
	void info();
	
	//注册
	int addUser(UserPojo userPojo);
	
	int addTouxiang(String userphone,String fileName);
	
	//判断手机号是否已注册
	UserPojo regUserphoneIfSame(String userphone);
	
	//判断昵称是否存在
	UserPojo regUsernameIfSame(String username);
	
	//登录
	UserPojo selectUser(String userphone,String userpwd);
	
	//查询用户by昵称
	List<UserPojo> selectUserByUsername(String username,String userphone);
	
	//查询用户的关注量by手机号
	int selectUserFollowByUp(String userphone);
	
	//查询用户的粉丝量by手机号
	int selectUserFansByUp(String userphone);
	
	//是否关注了by自身用户和被查用户
	int selIfFolByUpAndFp(String userphone,String followphone);
	
	//取消关注
	void delFolByUpAndFp(String userphone,String followphone);
	
	//取消粉丝
	void delFansByUpAndFp(String userphone,String fansphone);
	
	//关注ta
	void addFolByUpAndFp(String userphone,String followphone);
	
	//添加粉丝
	void addFansByUpAndFp(String userphone,String fansphone);
	
	//修改昵称
	void updateUsername(String username,String userphone);
	
	//修改邮箱
	void updateUseremail(String useremail,String userphone);
	
	//修改密码
	void updateUserpwd(String userpwd,String userphone);
	
	//头像
	void upTouxiang(String fileName,String userphone);
	
	String findTouxiang(String userphone);
	
	//个性签名
	String findUsertext(String userphone);
	
	void updateUsertext(String usertext,String userphone);
	
	//Session
	UserPojo session(String userphone);

}
