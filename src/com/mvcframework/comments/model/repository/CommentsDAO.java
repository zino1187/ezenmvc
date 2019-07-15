package com.mvcframework.comments.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvcframework.comments.model.domain.Comments;

import pool.PoolManager;

public class CommentsDAO {
	PoolManager pool = PoolManager.getInstance();
	
	//댓글 등록!!
	public int insert(Comments comments) {
		int result = 0; 
		Connection con=null;
		PreparedStatement pstmt=null;
		con=pool.getConnection();

		String sql="insert into comments(comments_id, news_id,writer, msg)";
		sql+=" values(seq_comments.nextval, ?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comments.getNews_id());
			pstmt.setString(2, comments.getWriter());
			pstmt.setString(3, comments.getMsg());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//모든 코멘트 가져오기 , 현재 보고 있는 원글에 딸려잇는...
	public List selectAll(int news_id) {
		List list = new ArrayList();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=pool.getConnection();
		String sql="select * from comments where news_id=?"; //원글이 무엇인지..
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comments comments = new Comments();
				
				comments.setComments_id(rs.getInt("comments_id"));
				comments.setNews_id(rs.getInt("news_id"));
				comments.setWriter(rs.getString("writer"));
				comments.setMsg(rs.getString("msg"));
				comments.setRegdate(rs.getString("regdate"));
				list.add(comments);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
}









