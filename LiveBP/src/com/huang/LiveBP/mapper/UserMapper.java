package com.huang.LiveBP.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.huang.LiveBP.pojo.UserPojo;

@Repository
public interface UserMapper {
	
	//注册用户
	@Insert("INSERT INTO l_user(userphone,username,userpwd,useremail,usersex) VALUES(#{userphone},#{username},#{userpwd},#{useremail},#{usersex})")
	int insertUser(UserPojo userPojo);
	
	@Insert("INSERT INTO l_userinfo(userphone,touxiang_path) VALUES(#{userphone},#{touxiang_path})")
	int addTouxiang(@Param("userphone") String userphone,@Param("touxiang_path") String touxiang_path);
	
	//判断手机号是否已注册
	@Select("SELECT * FROM l_user WHERE userphone=#{userphone}")
	UserPojo regUserphoneIfSame(@Param("userphone") String userphone);
	
	//判断昵称是否存在
	@Select("SELECT * FROM l_user WHERE username=#{username}")
	UserPojo regUsernameIfSame(@Param("username") String username);
	
	//登录
	@Select("SELECT * FROM l_user WHERE userphone=#{userphone} and userpwd=#{userpwd}")
	UserPojo selectUser(@Param("userphone") String userphone,@Param("userpwd") String userpwd);
	
	//Session
	@Select("SELECT * FROM l_user WHERE userphone=#{userphone}")
	UserPojo session(@Param("userphone") String userphone);
	
	//查询用户by昵称
	@Select("<script>"
			+"SELECT * FROM l_user"
			+"<if test='username!=null and username!=\"\"'>"
			+" WHERE username LIKE CONCAT('%',#{username},'%') AND userphone!=#{userphone}"
			+"</if>"
			+" ORDER BY userphone"
			+"</script>")
	List<UserPojo> selectUserByUsername(@Param("username") String username,@Param("userphone") String userphone);
	
	//查询用户的关注量by手机号
	@Select("SELECT count(*) FROM l_follow WHERE userphone=#{userphone}")
	int selectUserFollowByUp(@Param("userphone") String userphone);
	
	//查询用户的粉丝数by手机号
	@Select("SELECT count(*) FROM l_fans WHERE userphone=#{userphone}")
	int selectUserFansByUp(@Param("userphone") String userphone);
	
	//查询直播间by直播间名字
	
	//判断是否相互关注
	@Select("SELECT count(*) FROM l_follow WHERE userphone=#{userphone} and followphone=#{followphone}")
	int selIfFolByUpAndFp(@Param("userphone") String userphone,@Param("followphone") String followphone);
	
	//取消关注
	@Delete("DELETE FROM l_follow WHERE userphone=#{userphone} and followphone=#{followphone}")
	void delFolByUpAndFp(@Param("userphone") String userphone,@Param("followphone") String followphone);
	
	//关注ta
	@Insert("INSERT INTO l_follow (userphone,followphone) VALUES (#{userphone},#{followphone})")
	void addFolByUpAndFp(@Param("userphone") String userphone,@Param("followphone") String followphone);
	
	//删除粉丝
	@Delete("DELETE FROM l_fans WHERE userphone=#{userphone} and fansphone=#{fansphone}")
	void delFansByUpAndFp(@Param("userphone") String userphone,@Param("fansphone") String fansphone);
	
	//添加粉丝
	@Insert("INSERT INTO l_fans (userphone,fansphone) VALUES (#{userphone},#{fansphone})")
	void addFansByUpAndFp(@Param("userphone") String userphone,@Param("fansphone") String fansphone);
	
	//修改昵称
	@Update("UPDATE l_user SET username = #{username} WHERE userphone = #{userphone}")
	void updateUsername(@Param("username") String username,@Param("userphone") String userphone);
	
	//修改邮箱
	@Update("UPDATE l_user SET useremail = #{useremail} WHERE userphone = #{userphone}")
	void updateUseremail(@Param("useremail") String useremail,@Param("userphone") String userphone);
	
	//修改密码
	@Update("UPDATE l_user SET userpwd = #{userpwd} WHERE userphone = #{userphone}")
	void updateUserpwd(@Param("userpwd") String userpwd,@Param("userphone") String userphone);
	
	//添加头像文件名
	@Update("UPDATE l_userinfo SET touxiang_path = #{touxiang_path} WHERE userphone = #{userphone}")
	void upTouxiang(@Param("touxiang_path") String touxiang_path,@Param("userphone") String userphone);
	
	//查找头像文件
	@Select("SELECT touxiang_path FROM l_userinfo WHERE userphone=#{userphone}")
	String findTouxiang(@Param("userphone") String userphone);
	
	//查找个性签名
	@Select("SELECT usertext FROM l_userinfo WHERE userphone=#{userphone}")
	String findUsertext(@Param("userphone") String userphone);
	
	@Update("UPDATE l_userinfo SET usertext = #{usertext} WHERE userphone = #{userphone}")
	void updateUsertext(@Param("usertext") String usertext,@Param("userphone") String userphone);
}
