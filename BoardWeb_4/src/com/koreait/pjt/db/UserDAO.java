package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	
	public static int insUserLoginHistory(UserLoginHistoryVO param) {
		
		String sql = "INSERT INTO t_user_loginhistory(i_history,i_user,ip_addr,os,browser) VALUES (seq_userloginhistory.nextval, ?, ?, ?, ?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {				//익명클래스.이건 그냥 클래스지 객체화한건 아니다. implements한거다. (인터페이스는 객체화가 안된다.)
			//따로 클래스를 만들어 객체화해서 써도 되지만 param을 또 보내야 하는 등 번거로워짐. 이렇게 쓰면 깔끔.
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1,param.getI_user());
				ps.setNString(2,param.getIp_addr());
				ps.setNString(3,param.getOs());
				ps.setNString(4,param.getBrowser());
			}
		});
	}
	
	public static int insUser(UserVO param) {
		
		String sql = "INSERT INTO t_user(i_user, user_id, upw, nm, email) VALUES (seq_user.nextval, ?,?,?,?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {				//익명클래스.이건 그냥 클래스지 객체화한건 아니다. implements한거다. (인터페이스는 객체화가 안된다.)
																						//따로 클래스를 만들어 객체화해서 써도 되지만 param을 또 보내야 하는 등 번거로워짐. 이렇게 쓰면 깔끔.
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1,param.getUser_id());
				ps.setNString(2,param.getUser_pw());
				ps.setNString(3,param.getNm());
				ps.setNString(4,param.getEmail());
				
			}

		});
	}
	
	//0:에러발생, 1:로그인 성공, 2:비밀번호 틀림, 3:아이디 없음
	public static int login(UserVO param) {
		
		String sql = "SELECT i_user, upw, nm FROM t_user WHERE user_id=?";
				
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1,  param.getUser_id());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				
				if(rs.next()) {					//레코드가 있음
					String dbPw = rs.getNString("upw");
					
					if(dbPw.equals(param.getUser_pw())) {	//로그인 성공(비밀번호 맞을 경우)
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);
						return 1;
					} else {								//로그인 실패.(비밀번호 틀릴 경우)
						return 2;
					}
				}else {							//레코드가 없음. (아이디 없음)
					return 3;						
				}
				
			}	
		});
	}
	
	public static UserVO selUser(int i_user) {
		String sql = "select user_id, nm, profile_img, email, r_dt from t_user where i_user=?";
		
		UserVO result = new UserVO();
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setUser_id(rs.getNString("user_id"));
					result.setNm(rs.getNString("nm"));
					result.setEmail(rs.getNString("email"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setR_dt(rs.getNString("r_dt"));
				}
				return 1;
			}
		});
		
		return result;
	}

	public static int updUser(UserVO param) {
		StringBuilder sb = new StringBuilder(" update t_user set m_dt = sysdate");			//문자열 합치기 할때 String 보다 퍼포먼스가 좋음.(특히 for문 안에서)
		
		if(param.getUser_pw() != null) {
			sb.append(", upw = '");
			sb.append(param.getUser_pw());
			sb.append("' ");
		}
		if(param.getNm() != null) {
			sb.append(", nm = '");
			sb.append(param.getNm());
			sb.append("' ");
		}
		if(param.getEmail() != null) {
			sb.append(", email = '");
			sb.append(param.getEmail());
			sb.append("' ");
		}
		if(param.getProfile_img() != null) {
			sb.append(", profile_img = '");
			sb.append(param.getProfile_img());
			sb.append("' ");
		}
		sb.append("where i_user = ");
		sb.append(param.getI_user());
		
		System.out.println("sb : " + sb.toString());
		
		
		return JdbcTemplate.executeUpdate(sb.toString(), new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException { }
			
		});
	}
	
}
