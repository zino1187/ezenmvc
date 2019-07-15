package com.mvcframework.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//���� ��Ʈ�ѷ����� Ŭ���̾�Ʈ�� ��û�� ���� ó���ϰ���������,
//���� ��Ʈ�ѷ��� �ξ�, ���͸��ϴ� ����� ������������ �ξ� ����
//�� ���� ����� �Ը� Ŭ���� ������ �����Ѵ�!!!
public class DispatcherServlet extends HttpServlet{
	JSONParser parser;
	FileReader reader;// 
	String realPath;
	
	//������ ȣ�� ��, �����̳ʷκ��� �ʱ�ȭ�ɶ� ȣ��Ǵ� �����ֱ� �޼���!!
	public void init(ServletConfig config) throws ServletException {
		String configPath=config.getInitParameter("contextConfigLocation");
		ServletContext application = config.getServletContext();
		realPath = application.getRealPath(configPath);
		System.out.println(realPath);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* ��� ��Ʈ�ѷ��� ���� ó�� ����
		 * 1) �ܿ�û�� �޴´� (������Ʈ�ѷ�)
		 * 2) �ܿ�û�� �м��Ѵ�(������Ʈ�ѷ�)
		 * 3)   �˸´� ���� ��ü�� �Ͻ�Ų�� ( ������Ʈ�ѷ�)
		 * 4)   �����Ұ� ������� ���� ( ������Ʈ�ѷ�) request ���� Ȱ��
		 * 5) �ܰ���� �����ش� ( ���� ��Ʈ�ѷ� )
		 */
		request.setCharacterEncoding("utf-8");
		
		//2�ܰ� : �м�) Ŭ���̾�Ʈ�� ���ϴ°� �������� �м�!!!
		//	  movie��ȭ�� ���� �����û, blood �������� ���� �����û
		System.out.println("Ŭ���̾�Ʈ�� ��û�߳׿�!!");
		
		//Ŭ���̾�Ʈ�� ��û�� ����� �ּ�~!!
		//�� ������ �� Ŭ���̾�Ʈ�� ��û ���а����� Ȱ���غ���!!
		String uri = request.getRequestURI();
		System.out.println("Ŭ���̾�Ʈ�� ��û URI"+uri);
		
		Controller controller=null;
		RequestDispatcher dis=null;
		//��ȭ���...
		
		//�ڹٿ��� new �����ڸ��� �ν��Ͻ��� ������ �� �մ°� �ƴϴ�!!!
		//Class Ŭ������ �̿��ϸ� newInstance() �޼���ε� �����ϴ�!!!
		Class controllerClass=null;
		
		//Ŭ�������� �ڹ� �ڵ�ȿ� ��������, ���ǹ� ������ ���ֱ� ����
		//�ܺ� �������Ϸκ��� Ŭ�������� ��ȸ����!!!
		try {
			reader = new FileReader(realPath);
			parser = new JSONParser();
			JSONObject json=(JSONObject) parser.parse(reader); //���̽� ���� �ν�!!! + �ؼ�!!
			JSONObject obj=(JSONObject)json.get("controller");
			
			String className = (String)obj.get(uri);
			System.out.println(uri+"�� ������ Ŭ�������� "+className);
			
			//���� ��Ʈ�ѷ��� �ν��Ͻ� �����ϱ�!!!
			controllerClass=Class.forName(className); //static ������ Ŭ���� �ڵ� Load!!
			//�ν��Ͻ� �����ϱ�~~
			controller = (Controller)controllerClass.newInstance();//new�ѰͰ� ����!
			controller.doRequest(request, response);//���� ��Ʈ�ѷ� ����~~
			
			//� �並 ����������� ������Ʈ�ѷ����� ����
			String viewKey= controller.getResultKey();
			//Ű���� �̿��Ͽ� ���Ī ��ü ���!!
			JSONObject obj2 = (JSONObject)json.get("view");
			String viewPage = (String)obj2.get(viewKey);
			System.out.println("�������� : "+viewPage);
			
			//��û�� ���� ������ ������ or ��û�� ����� �Ұ��̳� �����ؾ���
			if(controller.isForward()) {
				dis = request.getRequestDispatcher(viewPage);
				dis.forward(request, response);//��� �������� ������~~~
			}else {
				//������ �޴� �������� ,������ URL�� �ٽ� �����ض�!!
				response.sendRedirect(viewPage);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	//�� ������ �Ҹ�ɶ�, ���� �ڿ��� �ִٸ� ����!!
	public void destroy() {
		if(reader !=null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}



















