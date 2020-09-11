package com.koreait.matzip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.vo.CodeDomain;

public class CommonDAO {
	
	public static List<CodeDomain> selCodeList(final int i_m) {
		List<CodeDomain> list = new ArrayList();				//List 는 ArrayList의 부모. ArrayList는 List를 implements 한 것이기 때문에 이렇게 사용 가능.
		
		String sql = " select i_m, cd, val from c_code_d where i_m = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1,  i_m);
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					CodeDomain cd = new CodeDomain();
					cd.setCd(rs.getInt("cd"));
					cd.setVal(rs.getNString("val"));
					list.add(cd);
				}
			}
		});
		
		return list;
	}
}
