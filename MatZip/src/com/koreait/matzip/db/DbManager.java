package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbManager {
	
	public static Connection getCon() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/matzip";
		String user = "root";
		String pw = "koreait2020";
		String className = "com.mysql.cj.jdbc.Driver";
		
		Class.forName(className);
		Connection con = DriverManager.getConnection(url, user, pw);
		System.out.println("DB 접속성공!");
		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) { try{ rs.close(); } catch(Exception e){} }
		close(con,ps);
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		if(ps!=null) { try{ ps.close(); } catch(Exception e){} }	
		if(con!=null) { try{ con.close(); } catch(Exception e){} }
	}
}
