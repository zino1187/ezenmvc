package com.mvcframework.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.controller.Controller;
import com.mvcframework.news.model.repository.NewsDAO;

public class ListController implements Controller{
	NewsDAO newsDAO = new NewsDAO();

	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List newsList=newsDAO.selectAll(); //3단계
		request.setAttribute("newsList", newsList);//4단계
	}

	public String getResultKey() {
		return "/views/news/list";
	}
	public boolean isForward() {
		return true;
	}
	
}
