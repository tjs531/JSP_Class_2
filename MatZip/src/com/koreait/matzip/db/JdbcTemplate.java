package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
	
	//select에 쓸 메소드
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			con=DbManager.getCon();
			ps=con.prepareStatement(sql);
			jdbc.prepared(ps);
			rs = ps.executeQuery();
			jdbc.executeQuery(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, ps, rs);
		}
	}
	
	//insert, update, delete
	public static int executeUpdate(String sql,JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con= DbManager.getCon(); 
			ps = con.prepareStatement(sql);
			
			jdbc.update(ps);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps);
		}
		
		return result;
	}
}
