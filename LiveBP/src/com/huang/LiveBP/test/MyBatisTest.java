package com.huang.LiveBP.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.huang.LiveBP.mapper.UserMapper;
import com.huang.LiveBP.pojo.UserPojo;
import com.huang.LiveBP.util.MyBatisUtil;

public class MyBatisTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertUser(){
		try {
			//��ò������ݿ��SqlSession
			SqlSession session = MyBatisUtil.getSession();
			//ʹ��session���UserMapper�Ĵ������getMapper
			UserMapper userMapper = session.getMapper(UserMapper.class);
			//�����û�
			UserPojo userPojo = new UserPojo();
			userPojo.setUserphone("13662920679");
			userPojo.setUsername("huangxitao");
			userPojo.setUserpwd("123456");
			userPojo.setUseremail("13662920679@163.com");
			userPojo.setUsersex("1");
			userMapper.insertUser(userPojo);
			//�������ύ����
			session.commit();
			//�ر�Session
			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
