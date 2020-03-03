package com.huang.LiveBP.service;

import java.util.List;

import com.huang.LiveBP.pojo.UserPojo;

public interface UserServiceImpl {
	
	void info();
	
	//ע��
	int addUser(UserPojo userPojo);
	
	int addTouxiang(String userphone,String fileName);
	
	//�ж��ֻ����Ƿ���ע��
	UserPojo regUserphoneIfSame(String userphone);
	
	//�ж��ǳ��Ƿ����
	UserPojo regUsernameIfSame(String username);
	
	//��¼
	UserPojo selectUser(String userphone,String userpwd);
	
	//��ѯ�û�by�ǳ�
	List<UserPojo> selectUserByUsername(String username,String userphone);
	
	//��ѯ�û��Ĺ�ע��by�ֻ���
	int selectUserFollowByUp(String userphone);
	
	//��ѯ�û��ķ�˿��by�ֻ���
	int selectUserFansByUp(String userphone);
	
	//�Ƿ��ע��by�����û��ͱ����û�
	int selIfFolByUpAndFp(String userphone,String followphone);
	
	//ȡ����ע
	void delFolByUpAndFp(String userphone,String followphone);
	
	//ȡ����˿
	void delFansByUpAndFp(String userphone,String fansphone);
	
	//��עta
	void addFolByUpAndFp(String userphone,String followphone);
	
	//��ӷ�˿
	void addFansByUpAndFp(String userphone,String fansphone);
	
	//�޸��ǳ�
	void updateUsername(String username,String userphone);
	
	//�޸�����
	void updateUseremail(String useremail,String userphone);
	
	//�޸�����
	void updateUserpwd(String userpwd,String userphone);
	
	//ͷ��
	void upTouxiang(String fileName,String userphone);
	
	String findTouxiang(String userphone);
	
	//����ǩ��
	String findUsertext(String userphone);
	
	void updateUsertext(String usertext,String userphone);
	
	//Session
	UserPojo session(String userphone);

}
