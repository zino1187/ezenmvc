<%@page import="com.mvcframework.news.model.domain.News"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	News news=(News)request.getAttribute("news");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box{border:1px solid #CCCCCC}
#writer,#title,#content{font-size:9pt;font-weight:bold;color:#7F7F7F;돋움}
input{
	font-size:9pt;
	border-left:1px solid #C3C3C3;
	border-top:1px solid #C3C3C3;
	border-right:1px solid #C3C3C3;
	border-bottom:1px solid #C3C3C3;
	color:#7F7F7F;돋움
}
#title input{width:250px;}
#content textarea{
width:503px;
border:0;
height:153;
background:url("/board/images/write_bg.gif");
border:#C3C3C3 1px solid 
}
#copyright{font-size:9pt;}
a{text-decoration:none}
img{border:0px}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	$("button").click(function(){
		registComments();
	});	
});
//댓글 등록 요청 ( 비동기 async)
function registComments(){
	$.ajax({
		url:"/comments/write.do",
		type:"post",
		data:{
			writer:$("#writer").val() , 
			msg:$("#msg").val(),
			news_id:<%=news.getNews_id()%>
		},
		success:function(data){
			//새로고침 없이 서버에서 전송된 xml or json 을 파싱하여 화면 갱신...	
			alert(data);
		}
	});
}

function del(){
	//삭제 요청~~~ .do
	if(confirm("삭제하시겠어요?")){
		location.href="/board/delete.do?board2_id=<%=news.getNews_id()%>";
	}	
}

//수정요청~~.do
function edit(){
	if(confirm("수정하시겠어요?")){
		form1.action="/board/edit.do";
		form1.method="post";
		form1.submit();
	}
}
</script>
</head>
<body>
<form name="form1">

<table id="box" align="center" width="603" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="/board/images/ceil.gif" width="603" height="25"></td>
  </tr>
  <tr>
    <td height="2" bgcolor="#6395FA"><img src="/board/images/line_01.gif"></td>
  </tr>
  <tr>
    <td height="1" bgcolor="#CCCCCC"></td>
  </tr>
	<tr>	
		<td id="list"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="25" align="center">작성자</td>
            <td><input type="text" name="writer" value="<%=news.getWriter()%>"></td>
          </tr>
          <tr>
            <td height="25" align="center">제목</td>
            <td><input type="text" name="title" value="<%=news.getTitle()%>"></td>
          </tr>
          <tr>
            <td align="center">내용</td>
            <td><textarea name="content" style=""><%=news.getContent() %></textarea></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
	</tr>
  <tr>
    <td height="30" align="right" style="padding-right:2px;">
	<img src="/board/images/write_btin.gif" width="61" height="20" onClick="edit()">
	<img src="/board/images/delete_btn.gif" width="61" height="20" onClick="del()"> <a href="list.html"><img src="/board/images/list_btn.gif" width="61" height="20" border="0"></a> </td>
  </tr>
  <tr>
    <td height="1" bgcolor="#CCCCCC"></td>
  </tr>
  <tr>
  	<td style="background:yellow">
  		<input type="text" id="msg" placeholder="메시지 입력" style="width:60%"/>
  		<input type="text" id="writer" placeholder="작성자 입력" style="width:20%"/>
  		<button type="button">댓글등록</button>
  	</td>
  </tr>
  <tr>
    <td height="20" align="center" id="copyright">Copyright zino All Rights Reserved </td>
  </tr>
</table>
</form>
</body>
</html>








