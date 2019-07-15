package com.mvcframework.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.controller.Controller;

public class DetailController implements Controller{

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	public String getResultKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
