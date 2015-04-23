package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import seven.tools.db.CusDAOException;
import seven.tools.db.CusDbUtils;

public class DbUtilsTest {

	@Test
	public void runTess() throws CusDAOException, SQLException{
		Connection conn = CusDbUtils.getConnection();
		CusDbUtils.getTranManager().beginTransaction();
		QueryRunner queryRunner = new QueryRunner();
		
		queryRunner.update(conn,"update user_general set name = ? where uid=?",new Object[]{"123",1412826});
		Map<String,Object> map = queryRunner.query(conn, "select * from user_general", new MapHandler());
		System.out.println(map);
		CusDbUtils.getTranManager().rollbackAndClose();
		
		conn = CusDbUtils.getConnection();
		map = queryRunner.query(conn, "select * from user_general", new MapHandler());
		System.out.println(map);
		
	}
	
	

}
