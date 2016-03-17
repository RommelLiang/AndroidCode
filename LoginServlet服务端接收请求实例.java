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
		String username = request.getParameter("username");	// ���õı�����: iso-8859-1
		String password = request.getParameter("password");
		
		// ����iso8859-1�ı��������������ת, ת�����ֽ�����, ��ʹ��utf-8��������ݽ���ת��, �ַ���
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		password = new String(password.getBytes("iso8859-1"), "utf-8");
		
		System.out.println("����: " + username);
		System.out.println("����: " + password);
		
		if("lisi".equals(username) && "123".equals(password)) {
			/*
			 * getBytes Ĭ�������, ʹ�õ�iso8859-1�ı���, ��������������û�е�ǰ�ַ�, 
			 * ��ʹ�õ�ǰϵͳ�µ�Ĭ�ϱ���: GBK
			 */ 
			response.getOutputStream().write("��¼�ɹ�".getBytes("utf-8"));
		} else {
			response.getOutputStream().write("��¼ʧ��".getBytes("utf-8"));
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
