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
		System.out.println("댓글 등록 원해?");
		
		String news_id  = request.getParameter("news_id");
		String writer = request.getParameter("writer");
		String msg = request.getParameter("msg");
		
		Comments comments = new Comments(); //댓글 한건담을 인스턴스!!
		comments.setNews_id(Integer.parseInt(news_id));
		comments.setWriter(writer);
		comments.setMsg(msg);
		
		//댓글 등록~
		int result =commentsDAO.insert(comments);
		System.out.println("댓글 등록 결과" + result);
	}

	public String getResultKey() {
		return "/views/comments/write";
	}
	public boolean isForward() {
		return true;
	}
}





