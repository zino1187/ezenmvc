package com.mvcframework.comments.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.comments.model.repository.CommentsDAO;
import com.mvcframework.controller.Controller;

//댓글 목록 보기 요청 컨트롤러!!! (결과뷰는 html 아닌, xml or json 으로 보내야함)
//비동기는 디자인영역을 뺀 데이터 영역만 전송하면 되니깐...
public class ListController implements Controller{
	CommentsDAO commentsDAO = new CommentsDAO();
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String news_id = request.getParameter("news_id");//포워딩으로 넘어옴

		//3단계
		List commentsList = commentsDAO.selectAll(Integer.parseInt(news_id));
		
		//4단계
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









