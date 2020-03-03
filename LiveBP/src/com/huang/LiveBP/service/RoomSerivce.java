package com.huang.LiveBP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.LiveBP.mapper.RoomMapper;
import com.huang.LiveBP.pojo.RoomPojo;

@Service("roomService")
public class RoomSerivce implements RoomServiceImpl{
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public void addRoom(RoomPojo roomPojo) {
		// TODO Auto-generated method stub
		roomMapper.insertRoom(roomPojo);
	}

}
