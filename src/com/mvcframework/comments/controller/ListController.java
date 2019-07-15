package com.mvcframework.comments.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.comments.model.repository.CommentsDAO;
import com.mvcframework.controller.Controller;

//��� ��� ���� ��û ��Ʈ�ѷ�!!! (������ html �ƴ�, xml or json ���� ��������)
//�񵿱�� �����ο����� �� ������ ������ �����ϸ� �Ǵϱ�...
public class ListController implements Controller{
	CommentsDAO commentsDAO = new CommentsDAO();
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String news_id = request.getParameter("news_id");//���������� �Ѿ��

		//3�ܰ�
		List commentsList = commentsDAO.selectAll(Integer.parseInt(news_id));
		
		//4�ܰ�
		request.setAttribute("commentsList", commentsList);
	}

	@Override
	public String getResultKey() {
		return "/views/comments/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}
	
}









