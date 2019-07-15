package com.mvcframework.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.controller.Controller;
import com.mvcframework.news.model.domain.News;
import com.mvcframework.news.model.repository.NewsDAO;

public class WriteController implements Controller{
	NewsDAO newsDAO = new NewsDAO();
	
	//3단계(비즈니스 로직 객체에게 일 시키기!!), 
	//4단계(뷰에 보여줄것이 있다면 저장) 
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title=request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);
		System.out.println("등록결과 "+result);
	}

	@Override
	public String getResultKey() {
		return "/views/news/write";
	}

	//글쓴 후엔, 새롭게 /list.do 를 요청하면 되므로 저장할 것이 없다!!
	public boolean isForward() {
		return false;
	}
	
}




