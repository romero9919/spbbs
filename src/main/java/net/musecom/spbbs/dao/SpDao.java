package net.musecom.spbbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import net.musecom.spbbs.dto.SpDto;
import net.musecom.spbbs.util.Static;

public class SpDao {
  
	DataSource dataSource;
	JdbcTemplate template = null;
	
	public SpDao() {
		
	   try {
		   
		   Context context = new InitialContext();
		   dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spbbs");
		   
	   }catch(NamingException e) {
		   e.printStackTrace();
	   }

	   template = Static.template;
	}
	

	public void write(String writer, String userid, String userpass, String title, String contents) {

		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				String sql = "insert into ndboard (writer, userid, userpass, title, contents) values (?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, writer);
				pstmt.setString(2, userid);
				pstmt.setString(3, userpass);
				pstmt.setString(4, title);
				pstmt.setString(5, contents);
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					System.out.println("등록" + rs.getInt(0));
					System.out.println("등록" + rs.getInt(1));
				}
				return pstmt;
			}
		});
	
	}
	
	
//	
//	public SpDto reply(String cNum) {
//		int iNum = Integer.parseInt(cNum);
//		SpDto dto = null;
//	    Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dataSource.getConnection();
//			String sql = "select * from spboard where num = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, iNum);
//			rs = pstmt.executeQuery();
//			
//			//dto�� ���
//			if(rs.next()) {
//				dto = new SpDto();
//				int num = rs.getInt("num");
//				int s_group = rs.getInt("s_group");
//				int s_step = rs.getInt("s_step");
//				int s_indent = rs.getInt("s_indent");
//				
//				dto.setNum(num);
//				dto.setS_group(s_group);
//				dto.setS_step(s_step);
//				dto.setS_indent(s_indent);
//			}			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}catch(Exception ee) {
//				
//			}
//		}
//		
//		return dto;
//	}
//	
//	public void replyok(int s_group, int s_step, int s_indent, String uname, String upass, String title, String content) {
//		
//		replyUpdate(s_group, s_step);
//		
//		String sql = "insert into spboard (s_group, s_step, s_indent, uname, upass, title, content) values (?,?,?,?,?,?,?)";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, s_group);
//			pstmt.setInt(2, s_step);
//			pstmt.setInt(3, s_indent);
//			pstmt.setString(4, uname);
//			pstmt.setString(5, upass);
//			pstmt.setString(6, title);
//			pstmt.setString(7, content);
//			pstmt.executeUpdate();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			}catch(Exception ee) {}
//		}
//		
//	}
	

	public SpDto update(String cNum) {
		int id = Integer.parseInt(cNum);
		SpDto dto = null;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from ndboard where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			

			if(rs.next()) {
				dto = new SpDto();
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String userid = rs.getString("userid");
				String userpass = rs.getString("userpass");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
                
				dto.setNum(num);
				dto.setWriter(writer);
				dto.setUserid(userid);
				dto.setUserpass(userpass);
				dto.setTitle(title);
				dto.setContents(contents);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception ee) {
				
			}
		}
		
		return dto;
	}
	
	public void updateok(String num, String writer, String userid, String userpass, String title, String contents) {
		
		int iNum = Integer.parseInt(num);
		String sql = "update ndboard set writer=?, userid=?, userpass=?, title=?, content=? where num = ?";
	   	Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, userid);
			pstmt.setString(3, userpass);
			pstmt.setString(4, title);
			pstmt.setString(5, contents);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception ee) {}			
		}
		
	}
	
//	private void replyUpdate(int s_group, int s_step) {
//		String sql = "update spboard set s_step = s_step + 1 where s_group = ? and s_step >= ?";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(sql);			
//			pstmt.setInt(1, s_group);
//			pstmt.setInt(2, s_step);
//			pstmt.executeUpdate();
//			
//		}catch(Exception e) {
//				e.printStackTrace();
//		}finally {
//				try {
//					if(pstmt != null) pstmt.close();
//					if(conn != null) conn.close();
//				}catch(Exception ee) {}
//		}
//	}
	

	public SpDto detail(String cNum) {
		int num = Integer.parseInt(cNum);
		String sql = "select * from ndboard where num = " + num;
		return template.queryForObject(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));
	
	}
	

	public ArrayList<SpDto> list(String pg){
		
		int listCount = 10;
		int page = Integer.parseInt(pg);
		int min = ( page -1 ) * listCount;
		String limit = " limit " + min + " , " + listCount;
		
		String sql = "select * from ndboard order by num desc" + limit;
		return (ArrayList<SpDto>) template.query(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));

	} //list
	
	public int totalRecord() {
		String sql = "select count(*) from ndboard";
		return template.queryForObject(sql, Integer.class);
	}
	
	public int totalRecord(String where) {
		String sql = "select count(*) from ndboard where 1 and " + where;
		return template.queryForObject(sql, Integer.class);
	}
	
	public void delete(String cNum) {
		int num = Integer.parseInt(cNum);
		
	   	   Connection conn = null;
		   PreparedStatement pstmt = null;
		   try {		   
			   conn = dataSource.getConnection();
			   String sql = "delete from ndboard where num = ?";
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, num);
			   pstmt.executeUpdate();
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }finally {
			   try {
				   if(pstmt != null) pstmt.close();
				   if(conn != null) conn.close();
			   }catch(Exception eee) {}
		   }
	}
	

//	private void hitAdd(int num) {
//		String sql = "update spboard set hit = hit + 1 where num = ?";
//		template.update(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, num);
//			}
//		});
//	}
	
}
