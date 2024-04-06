package net.musecom.spbbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import net.musecom.spbbs.dto.SpCommentDto;
import net.musecom.spbbs.dto.SpDto;
import net.musecom.spbbs.util.Static;

public class SpCommentDao {

	DataSource dataSource;
	JdbcTemplate template = null;
	
	public SpCommentDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spbbs");
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
		template = Static.template;
	}
	
	public ArrayList<SpCommentDto> list(){
		
		String sql = "select * from ndboard_comment where ndboardNum = ? order by num desc";
		return (ArrayList<SpCommentDto>) template.query(sql, new BeanPropertyRowMapper<SpCommentDto>(SpCommentDto.class));

	}
	
	public void write(int ndboardNum, String username, String userpass, String userid, String comment) {

		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				String sql = "insert into ndboard_comment (ndboardNum, username, userpass, userid, comment) values (?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, ndboardNum);
				pstmt.setString(2, username);
				pstmt.setString(3, userpass);
				pstmt.setString(4, userid);
				pstmt.setString(5, comment);
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					System.out.println("등록" + rs.getInt(0));
					System.out.println("등록" + rs.getInt(1));
				}
				return pstmt;
			}
		});
	
	}
	
	
	
}
