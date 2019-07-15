package pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//웹기반에서 요청마다 커넥션을 연결하고, 응답시, 연결을 끊는
//단점을 보완하기 위한 커넥션 풀링 객체 정의~
public class PoolManager {
	private static PoolManager instance;
	InitialContext context; //java Naming directory interface 의 검색 객체
	//자원을 클래스안에 두지 않고 외부의 설정파일로 빼놓아 유지보수성을 높일
	//수 있는 기술을 가리킴.. ( MS : 액티브 디렉토리 기술과 비슷)
	DataSource ds;
	
	private PoolManager() {
		try {
			context = new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//ds로부터 커넥션 하나 대여하기!~!
	public Connection getConnection() {
		Connection con=null;
		try {
			con=ds.getConnection(); //풀로 부터 커넥션 한개 반환!!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//풀로 돌려보내기 ( 반환 ) 
	public void freeConnection(Connection con) { //접속만
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt) {//DML
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void freeConnection(Connection con, PreparedStatement pstmt,ResultSet rs) {//select
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}









