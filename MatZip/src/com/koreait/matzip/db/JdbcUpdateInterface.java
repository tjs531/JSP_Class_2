package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcUpdateInterface {			//인터페이스, 추상클래스는 부모역할만 한다.(자식을 가리킬때만 사용된다. 객체화 안됨)
	void update(PreparedStatement ps) throws SQLException ; 		//public abstract 키워드는 생략해도 자동으로 넣어진다.(abstract : 추상메소드)
}
