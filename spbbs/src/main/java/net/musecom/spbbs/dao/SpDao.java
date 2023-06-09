package net.musecom.spbbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import net.musecom.spbbs.dto.SpDto;
import net.musecom.spbbs.util.Static;

//db ���� Ŭ����
public class SpDao {
  
	DataSource dataSource;
	JdbcTemplate template = null;
	
	//�����ڿ��� DB ����
	public SpDao() {
	//���߿� �� ����	
	   try {
		   
		   Context context = new InitialContext();
		   dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spbbs");
		   
	   }catch(NamingException e) {
		   e.printStackTrace();
	   }
	 //�������
	   template = Static.template;
	}
	
	//�۾���
	public void write(String uname, String upass, String title, String content) {
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			String sql = "insert into spboard (uname, upass, title, content) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, uname);
			pstmt.setString(2, upass);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			
			pstmt.executeUpdate();
		
			rs = pstmt.getGeneratedKeys(); //���� ���� �� ������ Ű �� ��ȯ
			pstmt.clearParameters(); //pstmt �ٽ� ����� ���� ���  		
			
			if(rs.next()) {
				int num = rs.getInt(1);
				try {			
					String query = "update spboard set s_group = ? where num = ?";
			        pstmt = conn.prepareStatement(query);
			        pstmt.setInt(1, num);
			        pstmt.setInt(2, num);
			        pstmt.executeUpdate();
				}catch(Exception ee) {}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
				   if(conn != null) conn.close();
			   }catch(Exception eee) {}
		 }
	}
	
	//��۾���
	public SpDto reply(String cNum) {
		int iNum = Integer.parseInt(cNum);
		SpDto dto = null;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from spboard where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
			rs = pstmt.executeQuery();
			
			//dto�� ���
			if(rs.next()) {
				dto = new SpDto();
				int num = rs.getInt("num");
				int s_group = rs.getInt("s_group");
				int s_step = rs.getInt("s_step");
				int s_indent = rs.getInt("s_indent");
				
				dto.setNum(num);
				dto.setS_group(s_group);
				dto.setS_step(s_step);
				dto.setS_indent(s_indent);
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
	//��� ����ϱ�
	public void replyok(int s_group, int s_step, int s_indent, String uname, String upass, String title, String content) {
		
		replyUpdate(s_group, s_step);
		
		String sql = "insert into spboard (s_group, s_step, s_indent, uname, upass, title, content) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_group);
			pstmt.setInt(2, s_step);
			pstmt.setInt(3, s_indent);
			pstmt.setString(4, uname);
			pstmt.setString(5, upass);
			pstmt.setString(6, title);
			pstmt.setString(7, content);
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
	
	//�� �����ϱ�
	public SpDto update(String cNum) {
		int iNum = Integer.parseInt(cNum);
		SpDto dto = null;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from spboard where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
			rs = pstmt.executeQuery();
			
			//dto�� ���
			if(rs.next()) {
				dto = new SpDto();
				int num = rs.getInt("num");
				String uname = rs.getString("uname");
				String upass = rs.getString("upass");
				String title = rs.getString("title");
				String content = rs.getString("content");
                
				dto.setNum(num);
				dto.setUname(uname);
				dto.setUpass(upass);
				dto.setTitle(title);
				dto.setContent(content);
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
	
	public void updateok(String num, String uname, String upass, String title, String content) {

        int inum = Integer.parseInt(num);		
		String sql = "update spboard set uname=?, upass=?, title=?, content=? where num = ?";
	   	Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			pstmt.setString(2, upass);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setInt(5, inum);
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
	
	private void replyUpdate(int s_group, int s_step) {
		String sql = "update spboard set s_step = s_step + 1 where s_group = ? and s_step >= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, s_group);
			pstmt.setInt(2, s_step);
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
	
	//���� ����
	public SpDto detail(String cNum) {
		
		int iNum = Integer.parseInt(cNum);
		hitAdd(iNum);  //��ȸ�� ����
		SpDto dto = null;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		     conn = dataSource.getConnection();
		     String sql = "select * from spboard where num = ?";
		     pstmt = conn.prepareStatement(sql);
		     pstmt.setInt(1, iNum);
		     rs = pstmt.executeQuery();
		     
		     //dto�� ��� �۾�
		     if(rs.next()) {
		    	  int num = rs.getInt("num");
		    	  int s_group = rs.getInt("s_group");
		    	  int s_step = rs.getInt("s_step"); 
		    	  int s_indent = rs.getInt("s_indent");
		    	  String uname = rs.getString("uname");
		    	  String upass = rs.getString("upass"); 
		    	  String title = rs.getString("title");
		    	  String content = rs.getString("content");
		    	  int ct = rs.getInt("ct");
		    	  int hit = rs.getInt("hit");
		    	  Timestamp wdate = rs.getTimestamp("wdate");
		    	   
                  dto = new SpDto(
		    		   num, s_group, s_step, s_indent, uname, upass, title, content, ct, hit, wdate	   
		    	  );
		     }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
				   if(conn != null) conn.close();
			   }catch(Exception eee) {}
		 }
		
		return dto;
	}
	
	//�����͸� �޾Ƽ� SpDto�� ����
	public ArrayList<SpDto> list(){
		
		String sql = "select * from spboard order by s_group desc, s_step asc";
		return (ArrayList<SpDto>) template.query(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));

/*		
	   ArrayList<SpDto> dtos = new ArrayList<SpDto>(); 
	
   	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   try {
	      conn = dataSource.getConnection();
	      String sql = "select * from spboard order by s_group desc, s_step asc";
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	   int num = rs.getInt("num");
	    	   int s_group = rs.getInt("s_group");
	    	   int s_step = rs.getInt("s_step"); 
	    	   int s_indent = rs.getInt("s_indent");
	    	   String uname = rs.getString("uname");
	    	   String upass = rs.getString("upass"); 
	    	   String title = rs.getString("title");
	    	   String content = rs.getString("content");
	    	   int ct = rs.getInt("ct");
	    	   int hit = rs.getInt("hit");
	    	   Timestamp wdate = rs.getTimestamp("wdate");
	    	   
	    	   SpDto dto = new SpDto(
	    		   num, s_group, s_step, s_indent, uname, upass, title, content, ct, hit, wdate	   
	    	   );
	    	   dtos.add(dto);
	      }
	   
	   }catch(Exception ee) {
		  ee.printStackTrace();
	   }finally {
		   try {
			   if(rs != null) rs.close();
			   if(pstmt != null) pstmt.close();
			   if(conn != null) conn.close();
		   }catch(Exception eee) {}
	   }
 
	   return dtos;
	   */
	
	} //list
	
	public void delete(String num) {
		
		   int iNum = Integer.parseInt(num);
	   	   Connection conn = null;
		   PreparedStatement pstmt = null;
		   try {		   
			   conn = dataSource.getConnection();
			   String sql = "delete from spboard where num = ?";
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, iNum);
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
	
	private void hitAdd(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update spboard set hit = hit + 1 where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int r = pstmt.executeUpdate();
			System.out.println("hit������Ʈ :" + r);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception ee) {}
		}
	}
	
}
