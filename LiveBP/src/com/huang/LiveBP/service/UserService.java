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
		System.out.println("����...");
	}
	
	//ע��
	@Override
	public int addUser(UserPojo userPojo) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(userPojo);
	}
	
	//�ж��ֻ����Ƿ���ע��
	@Override
	public UserPojo regUserphoneIfSame(String userphone) {
		// TODO Auto-generated method stub
		UserPojo regUserphoneIfSame = userMapper.regUserphoneIfSame(userphone);
		return regUserphoneIfSame;
	}
	
	//�ж��ǳ��Ƿ����
	@Override
	public UserPojo regUsernameIfSame(String username) {
		// TODO Auto-generated method stub
		UserPojo regUsernameIfSame = userMapper.regUsernameIfSame(username);
		return regUsernameIfSame;
	}
	
	//��¼
	@Override
	public UserPojo selectUser(String userphone,String userpwd) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(userphone,userpwd);
	}
	
	//��ѯ�û�by�ǳ�
	@Override
	public List<UserPojo> selectUserByUsername(String username,String userphone) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUsername(username,userphone);
	}
	
	//��ѯ�û��Ĺ�ע��by�ֻ���
	public int selectUserFollowByUp(String userphone) {
		return userMapper.selectUserFollowByUp(userphone);
	}
	
	//�Ƿ��ע��by�����û��ͱ����û�
	@Override
	public int selIfFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		return userMapper.selIfFolByUpAndFp(userphone, followphone);
	}
	
	//��ѯ�û��ķ�˿��by�ֻ���
	@Override
	public int selectUserFansByUp(String userphone) {
		// TODO Auto-generated method stub
		return userMapper.selectUserFansByUp(userphone);
	}
	
	//ȡ����ע
	@Override
	public void delFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		userMapper.delFolByUpAndFp(userphone, followphone);
	}
	
	//ȡ����˿
	@Override
	public void delFansByUpAndFp(String userphone, String fansphone) {
		// TODO Auto-generated method stub
		userMapper.delFansByUpAndFp(userphone, fansphone);
	}
	
	//��עta
	@Override
	public void addFolByUpAndFp(String userphone, String followphone) {
		// TODO Auto-generated method stub
		userMapper.addFolByUpAndFp(userphone, followphone);
	}
	
	//��ӷ�˿ 
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
