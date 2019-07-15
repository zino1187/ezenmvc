package com.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 요청을 처리하는 모든 컨트롤러들을 같은 자료형으로
//묶기 위한 클래스...
public interface Controller {  //자료형+구현강제
	//이 객체를 상속받는 모든~컨트롤러는 반드시 아래의 메서드명으로 정의해
	//야 하고, 반드시자신에게 맞게 오버라이딩 하도록 강제하자!!1
	//구현강제!!!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public String getResultKey();//직접적인 자원명을 명시하지 않는다!!
	public boolean isForward();//포워딩 여부를 결정짓는 메서드!!
}








