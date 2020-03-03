package com.huang.LiveBP.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.huang.LiveBP.pojo.RoomPojo;

@Repository
public interface RoomMapper {
	
	@Insert("INSERT INTO l_room(roomid,userid) VALUES(#{roomid},#{userid})")
	void insertRoom(RoomPojo roomPojo);

}
