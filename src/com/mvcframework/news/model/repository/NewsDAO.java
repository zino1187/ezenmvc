package com.mvcframework.news.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvcframework.news.model.domain.News;

import pool.PoolManager;

//CRUD , 파일 업로드??
public class NewsDAO {
	PoolManager pool = PoolManager.getInstance();
	
	public int insert(News news) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=pool.getConnection();
		String sql="insert into news(news_id,title,writer,content)";
		sql +=" values(seq_news.nextval, ?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			result=pstmt.executeUpdate(); //쿼리 수행!!!!
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		List list = new ArrayList();
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		con=pool.getConnection();
		String sql="select * from news order by news_id desc";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//rs를 대신할 대체재 !!
			while(rs.next()) {
				News news = new News();
				
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
		
	}
	
	//한건 가져오기
	public News select(int news_id) {
		News news=null;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		con=pool.getConnection();
		String sql="select * from news where news_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			//rs를 대신할 대체재 !!
			if(rs.next()) {
				news = new News();
				
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return news;
		
	}	
}












