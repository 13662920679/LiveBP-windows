package com.huang.LiveBP.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.huang.LiveBP.basic.controller.BasicController;
import com.huang.LiveBP.pojo.UserPojo;
import com.huang.LiveBP.service.UserServiceImpl;

//import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSON;

@Controller
@RequestMapping("/user")
public class UserController{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@ModelAttribute
	@RequestMapping(path="/regUser")
	public void saveUser(HttpServletResponse response,HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String userphone = request.getParameter("phone");
		String username = request.getParameter("name");
		String userpwd = request.getParameter("pwd");
		String useremail = request.getParameter("mail");
		String usersex = request.getParameter("sex");
		System.out.println(username);
		UserPojo user = new UserPojo();
		user.setUserphone(userphone);
		user.setUsername(username);
		user.setUserpwd(userpwd);
		user.setUseremail(useremail);
		user.setUsersex(usersex);
		int addUser = userServiceImpl.addUser(user);
		if(addUser>0) {
			userServiceImpl.addTouxiang(userphone, "touxiang.png");
			response.sendRedirect("http://localhost:8082/LiveBP/login.html");
		}else {
			response.sendRedirect("http://localhost:8082/LiveBP/reg.html");
		}
		

	}
	
	@ModelAttribute
	@RequestMapping(path="/regUserphoneIfSame")
	public void regUserphoneIfSame(HttpServletResponse response,HttpServletRequest request,String userphone) throws IOException {
		PrintWriter pw = response.getWriter();
		System.out.println(userphone);
		UserPojo regUserphoneIfSame = userServiceImpl.regUserphoneIfSame(userphone);
		
		System.out.println(regUserphoneIfSame);
		JSONObject json = new JSONObject();
		
		if(regUserphoneIfSame==null) {
			System.out.println("手机号尚未注册");
			json.put("result", true);
			pw.print(json);
			pw.flush();
			pw.close();

		}else {
			System.out.println("手机号已注册");
			json.put("result", false);
			pw.print(json);
			pw.flush();
			pw.close();
		}
	}
	
	
	@ModelAttribute
	@RequestMapping(path="/regUsernameIfSame")
	public boolean regUsernameIfSame(HttpServletResponse response,HttpServletRequest request,String username) throws IOException {
		PrintWriter pw = response.getWriter();
		System.out.println(username);
		UserPojo regUsernameIfSame = userServiceImpl.regUsernameIfSame(username);
		
		System.out.println(regUsernameIfSame);
		JSONObject json = new JSONObject();
		
		if(regUsernameIfSame==null) {
			System.out.println("没有相同昵称的");
			json.put("result", true);
			pw.print(json);
			pw.flush();
			pw.close();
			return true;
		}else {
			System.out.println("有相同昵称的");
			json.put("result", false);
			pw.print(json);
			pw.flush();
			pw.close();
			return false;
		}
	}
	
	@ModelAttribute
	@RequestMapping(path="/login")
	public void loginUser(HttpServletResponse response,HttpServletRequest request,String userphone,String userpwd) throws IOException{
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		
		UserPojo userPojo = userServiceImpl.selectUser(userphone, userpwd);
		
		if(userPojo==null) {
			System.out.println("手机号或密码错误");
//			response.getWriter().write("false");
			json.put("message", false);
			pw.print(json);
			pw.flush();
			pw.close();
			System.out.println("手机号或密码错误2");
//			return "false";
		}else {
			System.out.println("登录成功1");
//			response.getWriter().write("true");
			json.put("message", true);
			json.put("userPojo", userPojo);
			pw.print(json);
			pw.flush();
			pw.close();
			session.setAttribute("userPojo", userPojo);
			System.out.println("登录成功2");
//			return "true";
		}	
	}
	
	@ModelAttribute
	@RequestMapping(path="/getUserSession")
	public void getUserSession(HttpServletResponse response,HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		System.out.println(user.getUsername());
		json.put("userphone", user.getUserphone());
		json.put("username", user.getUsername());
		json.put("userpwd", user.getUserpwd());
		json.put("useremail", user.getUseremail());
		json.put("usersex", user.getUsersex());
		pw.print(json.toString());
		pw.flush();
		pw.close();
		System.out.println(user.getUserphone()+user.getUsername());
	}
	
	@ModelAttribute
	@RequestMapping(path="/getUserSession2")
	public void getUserSession2(HttpServletResponse response,HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
//		JSONObject json = new JSONObject();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
//		json.put("username", user.getUsername());
		pw.print(user.getUsername());
		pw.flush();
		pw.close();
		
	}
	
	
	
	@ModelAttribute
	@RequestMapping(path="getUsersex")
	public void getUsersex(HttpServletResponse response,HttpServletRequest request) throws IOException {
		System.out.println("sex");
		JSONObject json = new JSONObject();
		json.put("bg", "0");
		System.out.println(json.toString());
		response.getWriter().print(json.toString());
	}
	
	@ModelAttribute
	@RequestMapping(path="getUsertext")
	public void getUsertext(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		UserPojo user = (UserPojo)request.getSession().getAttribute("userPojo");
		String findUsertext = userServiceImpl.findUsertext(user.getUserphone());
		System.out.println(findUsertext);
		JSONObject json = new JSONObject();
		json.put("usertext", findUsertext);
		response.getWriter().print(json.toString());
	}
	
	
	@ModelAttribute
	@RequestMapping(path="/updateUsername")
	public void updateUsername(HttpServletResponse response,HttpServletRequest request,String newUsername) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		UserPojo userpojo = userServiceImpl.regUsernameIfSame(newUsername);
//		UserController userController = new UserController();
//		boolean regUsernameIfSame = userController.regUsernameIfSame(response, request, newUsername);
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		if(userpojo==null) {
			userServiceImpl.updateUsername(newUsername, user.getUserphone());
			UserPojo session2 = userServiceImpl.session(user.getUserphone());
			session.setAttribute("userPojo", session2);
			json.put("result", true);
			json.put("newUsername", newUsername);
			pw.print(json);
			pw.flush();
			pw.close();
		}else {
			json.put("result", false);
			pw.print(json);
			pw.flush();
			pw.close();
		}
	}
	
	@ModelAttribute
	@RequestMapping(path="/updateUsertext")
	public void updateUsertext(HttpServletResponse response,HttpServletRequest request,String newUsertext) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		userServiceImpl.updateUsertext(newUsertext, user.getUserphone());		
		JSONObject json = new JSONObject();
		json.put("newUsertext", newUsertext);
		response.getWriter().print(json.toString());
	}
	
	@ModelAttribute
	@RequestMapping(path="/updateUseremail")
	public void updateUseremail(HttpServletResponse response,HttpServletRequest request,String newUseremail) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		userServiceImpl.updateUseremail(newUseremail, user.getUserphone());
		
		UserPojo session2 = userServiceImpl.session(user.getUserphone());
		session.setAttribute("userPojo", session2);
		
		
		
		JSONObject json = new JSONObject();
		json.put("newUseremail", newUseremail);
		response.getWriter().print(json.toString());
	}
	
	@ModelAttribute
	@RequestMapping(path="/updateUserpwd")
	public void updateUserpwd(HttpServletResponse response,HttpServletRequest request,String newUserpwd) throws IOException {
		System.out.println("updateUserpwd");
		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		userServiceImpl.updateUserpwd(newUserpwd, user.getUserphone());
		
		UserPojo session2 = userServiceImpl.session(user.getUserphone());
		session.setAttribute("userPojo", session2);
		
		session.invalidate();
		
		JSONObject json = new JSONObject();
		json.put("newUserpwd", newUserpwd);
		response.getWriter().print(json.toString());
	}

	@ModelAttribute
	@RequestMapping(path="/selectBySousuo")
	public void selectBySousuos(HttpServletResponse response,HttpServletRequest request,String sousuo) throws IOException{
//		//获取当前页参数，第一次访问为空
//		  String currPage = request.getParameter("currenPage");
//		// 判断，如果为空，则设置为1
//		  if (currPage == null || "".equals(currPage.trim())) {
//		   currPage = "1";
//		  }
//		//调用service返回分页类实例
//		  PageBean<UserPojo> pageBean=new PageService().getPage(currPage);
//		//设置相应文本类型
//		  response.setContentType("application/json;charset=utf-8");
//		//响应前端
		  
//		 username="黄";
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		UserPojo user_this = (UserPojo)session.getAttribute("userPojo");
		
		List<UserPojo> userList = userServiceImpl.selectUserByUsername(sousuo,user_this.getUserphone());
//		JSONObject json = new JSONObject();
		JSONArray jsons = new JSONArray();
		

//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("userList:[");
//		sb.append("]");
		
		
//		json.put("userList", userList);
		
		for(int i=0;i<userList.size();i++) {
			UserPojo user = new UserPojo();
			user = userList.get(i);
			JSONObject json = new JSONObject();
			json.put("userphone", user.getUserphone());
			json.put("username", user.getUsername());
			if(user.getUsersex().equals("1")) {
				json.put("usersex", "男");
			}else {
				json.put("usersex", "女");
			}
//			json.put("usersex", user.getUsersex());
			int selectUserFollowByUp = userServiceImpl.selectUserFollowByUp(user.getUserphone());
			json.put("userFollow", selectUserFollowByUp);
			int selectUserFansByUp = userServiceImpl.selectUserFansByUp(user.getUserphone());
			json.put("userFans", selectUserFansByUp);
			//HttpSession session = request.getSession();
			UserPojo userPojo = (UserPojo)session.getAttribute("userPojo");
			int selIfFolByUpAndFp = userServiceImpl.selIfFolByUpAndFp(userPojo.getUserphone(), user.getUserphone());
			String ifFol=null;
			if(selIfFolByUpAndFp>0) {
				ifFol = "已关注";
			}else {
				ifFol = "关注";
			}
			json.put("ifFollow", ifFol);
			jsons.put(json);
//			System.out.println(userPojo.getUsername());
			System.out.println(userList.get(i).getUsername()+":"+userList.get(i).getUsersex());
		}
		
		PrintWriter pw = response.getWriter();
		pw.print(jsons);
		pw.flush();
		pw.close();
		
//		Iterator<UserPojo> userIter = userList.iterator();
//		while(userIter.hasNext()) {
//			System.out.println(userIter.next().getUsername());
//		}
//		System.out.println(userList.size());
		
	}
	
	@ModelAttribute
	@RequestMapping(path="/delFolByUpAndFp")
	public void delFolAndFans(HttpServletResponse response,HttpServletRequest request,String followphone) throws IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		String userphone = user.getUserphone();
		
		userServiceImpl.delFolByUpAndFp(userphone, followphone);
		System.out.println("成功取消关注");
		userServiceImpl.delFansByUpAndFp(followphone, userphone);
		System.out.println("成功取消粉丝");
	}
	
	@ModelAttribute
	@RequestMapping(path="/addFolByUpAndFp")
	public void addFolAndFans(HttpServletResponse response,HttpServletRequest request,String followphone) throws IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		String userphone = user.getUserphone();
		
		userServiceImpl.addFolByUpAndFp(userphone, followphone);
		System.out.println("成功关注");
		userServiceImpl.addFansByUpAndFp(followphone, userphone);
		System.out.println("成功添加粉丝");
	}
	
	@ModelAttribute
	@RequestMapping(path="/uptouxaing")
	public JSONObject uptouxaing(HttpServletResponse response,HttpServletRequest request,@RequestParam( value="file",required=false)MultipartFile file) throws IllegalStateException, IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		//解析请求中的数据  
//      MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;  
//      MultipartFile file = mpRequest.getFile("file");
		System.out.println("aaaaaaaa");
		System.out.println(file.getOriginalFilename());
		
		String fileFullname = file.getOriginalFilename();
		
		String type = fileFullname.substring(fileFullname.lastIndexOf(".") + 1);
		System.out.println(type);
		
		String fileName = user.getUserphone()+"."+type;
		
		userServiceImpl.upTouxiang(fileName, user.getUserphone());
		
		InputStream inputStream = file.getInputStream();
		
		OutputStream os = null;

	    try {
	      String path = "C:\\Users\\13662\\Desktop\\img";
	      
	      // 2、保存到临时文件
	      // 1K的数据缓冲
	      byte[] bs = new byte[1024];
	      // 读取到的数据长度
	      int len;
	      // 输出的文件流保存到本地文件
	      File tempFile = new File(path);
	      if (!tempFile.exists()) {
	        tempFile.mkdirs();
	      }
	      os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
	      // 开始读取
	      while ((len = inputStream.read(bs)) != -1) {
	        os.write(bs, 0, len);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // 完毕，关闭所有链接
	      try {
	        os.close();
	        inputStream.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }

	    }
		
		JSONObject json = new JSONObject();
		json.put("tf", true);
		return json;
	}
	
	@ModelAttribute
	@RequestMapping(path="/showTouxiang")
	public void showTouxiang(HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("text/html;charset=utf-8");
		//response.setContentType("application/json;charset=utf-8");
		response.setContentType("text/html;charset=UTF-8");
		//FileInputStream fileInputStream = new FileInputStream("C:\\Users\\13662\\Desktop\\img\\13662920679.png");
		
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		String findTouxiang = userServiceImpl.findTouxiang(user.getUserphone());
		
		File file = new File("C:\\Users\\13662\\Desktop\\img\\"+findTouxiang);
		
		System.out.println(file.getName());
		
		// 以byte流的方式打开文件 d:\1.gif
		FileInputStream hFile = new FileInputStream(file);
		//得到文件大小
		int i=hFile.available();
		byte data[]=new byte[i];
		//读数据
		hFile.read(data);
		//得到向客户端输出二进制数据的对象
		OutputStream toClient=response.getOutputStream();
		//输出数据
		toClient.write(data);
		toClient.flush();
		toClient.close();
		hFile.close();
	
		System.out.println(file.getName());
		
	}
	
	@ModelAttribute
	@RequestMapping(path="/follows")
	public void follows(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		int selectUserFollowByUp = userServiceImpl.selectUserFollowByUp(user.getUserphone());
		JSONObject json = new JSONObject();
		json.put("follows", selectUserFollowByUp);
		response.getWriter().print(json.toString());
	}
	
	@ModelAttribute
	@RequestMapping(path="/fans")
	public void fans(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserPojo user = (UserPojo)session.getAttribute("userPojo");
		int selectUserFansByUp = userServiceImpl.selectUserFansByUp(user.getUserphone());
		JSONObject json = new JSONObject();
		json.put("fans", selectUserFansByUp);
		response.getWriter().print(json.toString());
	}
	
}
