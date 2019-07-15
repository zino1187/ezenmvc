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
	
	//3�ܰ�(����Ͻ� ���� ��ü���� �� ��Ű��!!), 
	//4�ܰ�(�信 �����ٰ��� �ִٸ� ����) 
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
		System.out.println("��ϰ�� "+result);
	}

	@Override
	public String getResultKey() {
		return "/views/news/write";
	}

	//�۾� �Ŀ�, ���Ӱ� /list.do �� ��û�ϸ� �ǹǷ� ������ ���� ����!!
	public boolean isForward() {
		return false;
	}
	
}




