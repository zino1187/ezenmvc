package com.mvcframework.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.controller.Controller;
import com.mvcframework.news.model.domain.News;
import com.mvcframework.news.model.repository.NewsDAO;

public class DetailController implements Controller{
	NewsDAO newsDAO = new NewsDAO();
	
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String news_id = request.getParameter("news_id");
		News news= newsDAO.select(Integer.parseInt(news_id));//3단계
		
		request.setAttribute("news", news);//4단계: 가져갈것이 잇음..
	}

	@Override
	public String getResultKey() {
		return "/views/news/detail";
	}

	@Override
	public boolean isForward() {
		return true;	
	}
	
}
