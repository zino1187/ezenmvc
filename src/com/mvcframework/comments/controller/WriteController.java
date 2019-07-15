package com.mvcframework.comments.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.comments.model.domain.Comments;
import com.mvcframework.comments.model.repository.CommentsDAO;
import com.mvcframework.controller.Controller;

public class WriteController implements Controller{
	CommentsDAO commentsDAO = new CommentsDAO();
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("��� ��� ����?");
		
		String news_id  = request.getParameter("news_id");
		String writer = request.getParameter("writer");
		String msg = request.getParameter("msg");
		
		Comments comments = new Comments(); //��� �ѰǴ��� �ν��Ͻ�!!
		comments.setNews_id(Integer.parseInt(news_id));
		comments.setWriter(writer);
		comments.setMsg(msg);
		
		//��� ���~
		int result =commentsDAO.insert(comments);
		System.out.println("��� ��� ���" + result);
	}

	public String getResultKey() {
		return "/views/comments/write";
	}
	public boolean isForward() {
		return true;
	}
}





