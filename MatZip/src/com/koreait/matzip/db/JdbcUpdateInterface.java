package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcUpdateInterface {			//�������̽�, �߻�Ŭ������ �θ��Ҹ� �Ѵ�.(�ڽ��� ����ų���� ���ȴ�. ��üȭ �ȵ�)
	void update(PreparedStatement ps) throws SQLException ; 		//public abstract Ű����� �����ص� �ڵ����� �־�����.(abstract : �߻�޼ҵ�)
}
