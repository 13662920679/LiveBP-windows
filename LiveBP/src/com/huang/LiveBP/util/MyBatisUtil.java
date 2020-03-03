package com.huang.LiveBP.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory factory;
	
	static {
		//创建SessionFactory对象
		try {
			InputStream inputStream = Resources.getResourceAsStream("myBatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  SqlSession getSession() {
		return factory.openSession();
	}

}
