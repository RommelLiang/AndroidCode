package com.itheima28.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");	// 采用的编码是: iso-8859-1
		String password = request.getParameter("password");
		
		// 采用iso8859-1的编码对姓名进行逆转, 转换成字节数组, 再使用utf-8编码对数据进行转换, 字符串
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		password = new String(password.getBytes("iso8859-1"), "utf-8");
		
		System.out.println("姓名: " + username);
		System.out.println("密码: " + password);
		
		if("lisi".equals(username) && "123".equals(password)) {
			/*
			 * getBytes 默认情况下, 使用的iso8859-1的编码, 但如果发现码表中没有当前字符, 
			 * 会使用当前系统下的默认编码: GBK
			 */ 
			response.getOutputStream().write("登录成功".getBytes("utf-8"));
		} else {
			response.getOutputStream().write("登录失败".getBytes("utf-8"));
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}

}
