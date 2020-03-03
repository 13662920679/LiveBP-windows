package com.huang.LiveBP.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.LiveBP.service.UserServiceImpl;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springmvc-config.xml"})
public class Demo01 {
	
	@Autowired
	public UserServiceImpl userServiceImpl;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInfo() {
		userServiceImpl.info();
	}

}
