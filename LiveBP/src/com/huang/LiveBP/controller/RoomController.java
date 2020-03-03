package com.huang.LiveBP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.LiveBP.pojo.RoomPojo;
import com.huang.LiveBP.service.RoomServiceImpl;

@Controller
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomServiceImpl roomServiceImpl;
	
	@RequestMapping("/newRoom")
	public String newRoom() {
		RoomPojo roomPojo = new RoomPojo();
		roomPojo.setRoomid("b");
		roomPojo.setUserid("100002");
		roomServiceImpl.addRoom(roomPojo);
		return "success";
	}
}
