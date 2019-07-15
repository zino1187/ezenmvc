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

//하위 컨트롤러에게 클라이언트의 요청을 직접 처리하게하지말고,
//메인 컨트롤러를 두어, 필터링하는 방법이 유지보수성에 훨씬 좋다
//이 개발 방법은 규모가 클수록 위력이 발휘한다!!!
public class DispatcherServlet extends HttpServlet{
	JSONParser parser;
	FileReader reader;// 
	String realPath;
	
	//생성자 호출 후, 컨테이너로부터 초기화될때 호출되는 생명주기 메서드!!
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
		/* 모든 컨트롤러의 업무 처리 순서
		 * 1) ●요청을 받는다 (메인컨트롤러)
		 * 2) ●요청을 분석한다(메인컨트롤러)
		 * 3)   알맞는 로직 객체에 일시킨다 ( 서브컨트롤러)
		 * 4)   저장할게 있을경우 저장 ( 서브컨트롤러) request 많이 활용
		 * 5) ●결과를 보여준다 ( 메인 컨트롤러 )
		 */
		request.setCharacterEncoding("utf-8");
		
		//2단계 : 분석) 클라이언트가 원하는게 무엇인지 분석!!!
		//	  movie영화에 대한 조언요청, blood 혈액형에 대한 조언요청
		System.out.println("클라이언트가 요청했네요!!");
		
		//클라이언트가 요청에 사용한 주소~!!
		//이 정보를 각 클라이언트의 요청 구분값으로 활용해보자!!
		String uri = request.getRequestURI();
		System.out.println("클라이언트의 요청 URI"+uri);
		
		Controller controller=null;
		RequestDispatcher dis=null;
		//영화라면...
		
		//자바에서 new 연산자만이 인스턴스를 생성할 수 잇는게 아니다!!!
		//Class 클래스를 이용하면 newInstance() 메서드로도 가능하다!!!
		Class controllerClass=null;
		
		//클래스명을 자바 코드안에 두지말고, 조건문 완전히 없애기 위해
		//외부 설정파일로부터 클래스명을 조회하자!!!
		try {
			reader = new FileReader(realPath);
			parser = new JSONParser();
			JSONObject json=(JSONObject) parser.parse(reader); //제이슨 파일 인식!!! + 해석!!
			JSONObject obj=(JSONObject)json.get("controller");
			
			String className = (String)obj.get(uri);
			System.out.println(uri+"에 동작할 클래스명은 "+className);
			
			//서브 컨트롤러의 인스턴스 생성하기!!!
			controllerClass=Class.forName(className); //static 영역에 클래스 코드 Load!!
			//인스턴스 생성하기~~
			controller = (Controller)controllerClass.newInstance();//new한것과 동일!
			controller.doRequest(request, response);//서브 컨트롤러 실행~~
			
			//어떤 뷰를 보여줘야할지 서브컨트롤러에게 문의
			String viewKey= controller.getResultKey();
			//키값을 이용하여 뷰매칭 객체 얻기!!
			JSONObject obj2 = (JSONObject)json.get("view");
			String viewPage = (String)obj2.get(viewKey);
			System.out.println("뷰페이지 : "+viewPage);
			
			//요청에 대한 응답은 포워딩 or 요청을 끊어야 할것이냐 선택해야함
			if(controller.isForward()) {
				dis = request.getRequestDispatcher(viewPage);
				dis.forward(request, response);//결과 페이지로 포워딩~~~
			}else {
				//응답을 받는 브라우저는 ,지정한 URL로 다시 접속해라!!
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
	
	//이 서블릿이 소멸될때, 닫을 자원이 있다면 닫자!!
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



















