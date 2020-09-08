package com.koreait.matzip.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.UserVO;
import com.koreait.matzip.db.JdbcSelectInterface;

public class UserDAO {
	public int join(UserVO param) {
		//int result = 0;
		
		String sql = "INSERT INTO t_user(user_id, user_pw, salt, nm) VALUES (?,?,?,?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {				
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1,param.getUser_id());
				ps.setNString(2,param.getUser_pw());
				ps.setNString(3,param.getSalt());
				ps.setNString(4,param.getNm());
			}
		});
	}
	
	public UserVO selUser(UserVO param) {		//i_user로도, user_id로도 가져올 수 있게
		UserVO result = new UserVO();
		
		String sql = "Select i_user, user_id, user_pw, nm, salt, profile_img, r_dt "
				+ " from t_user where ";
		
		if(param.getI_user() > 0) {
			sql += " i_user = " + param.getI_user();
		}else if(param.getUser_id() != null && !"".equals(param.getUser_id())) {
			sql += " user_id = '" + param.getUser_id() + "' ";
		}
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setI_user(rs.getInt("i_user"));
					result.setUser_id(rs.getNString("user_id"));
					result.setUser_pw(rs.getNString("user_pw"));
					result.setNm(rs.getNString("nm"));
					result.setSalt(rs.getNString("salt"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setR_dt(rs.getString("r_dt"));
				}
			}
		});
		
		return result;
	}
}
