package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardVO;

public class BoardCmtDAO {
	public static int insCmt(BoardCmtVO param) {
		String sql = "insert into t_board4_cmt(i_cmt, i_board,i_user,cmt) values (seq_board4_cmt.nextval,?,?,?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCmt());
			}
		});
		
	}
	
	public static int updCmt(BoardCmtVO param) {
		String sql = "update t_board4_cmt set cmt=?, m_dt=sysdate where i_cmt=? and i_user=?";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_cmt());
				ps.setInt(3, param.getI_user());
			}
		});
	}
	
	public static int delCmt(BoardCmtVO vo) {
		String sql = "delete from t_board4_cmt where i_cmt=? and i_user=?";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo.getI_cmt());
				ps.setInt(2, vo.getI_user());
			}
		});
	}
	
	public static List<BoardCmtVO> selCmtList(BoardVO param) {
		final List<BoardCmtVO> list = new ArrayList();			//레퍼런스변수에 final 붙이면 '주소값'을 변경할 수 없다. (그 안에 값을 넣고 빼고 하는건 가능)
		
		String sql = "select A.i_cmt, A.i_user, A.cmt, B.profile_img, B.nm, A.r_dt, A.m_dt from t_board4_cmt A LEFT JOIN t_user B on A.i_user = B.i_user where A.i_board = ? order by i_cmt";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException { 
				ps.setInt(1, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_cmt = rs.getInt("i_cmt");
					int i_user = rs.getInt("i_user");
					String cmt = rs.getNString("cmt");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					String m_dt = rs.getNString("m_dt");
					String profile_img = rs.getNString("profile_img");
					
					BoardCmtVO vo = new BoardCmtVO();
					
					vo.setI_cmt(i_cmt);
					vo.setI_user(i_user);
					vo.setCmt(cmt);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
					vo.setM_dt(m_dt);
					vo.setProfile_img(profile_img);
					
					list.add(vo);
				}
				return 1;
			}
		});
		return list;
	}
}
