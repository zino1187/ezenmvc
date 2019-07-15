<%@page import="com.mvcframework.comments.model.domain.Comments"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//ListController 가 전달한 결과물!!
	List<Comments> commentsList = (List)request.getAttribute("commentsList");

	//클라이언트가 비동기 방식으로 요청할 경우, 뷰는 디자인을 보내줄 필요X
	StringBuilder sb = new StringBuilder();

	sb.append("{");
	sb.append("\"commentsList\":[");
	for(int i=0;i<commentsList.size();i++){
		Comments comments=commentsList.get(i); //한건 끄집어 내기!
		sb.append("{");
		sb.append("\"comments_id\":  "+comments.getComments_id()+"  ,");
		sb.append("\"news_id\":"+comments.getNews_id()+",");
		sb.append("\"writer\" : \""+comments.getWriter()+"\",");
		sb.append("\"msg\" :\""+comments.getMsg()+"\",");
		sb.append("\"regdate\":\""+comments.getRegdate()+"\"");
		if(i < commentsList.size()-1){
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");	
	
	out.print(sb.toString());
%>








