package seven.tools.db;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * 事务管理器
 * @author qiujy
 * @version 0.9Beta
 */
public class CusTransactionManager {
	private Connection conn;
	
	protected CusTransactionManager(Connection conn) {
		this.conn = conn;
	}
	
	/** 开启事务 */
	public void beginTransaction() throws CusDAOException{
		try {
			conn.setAutoCommit(false);  //把事务提交方式改为手工提交
		} catch (SQLException e) {
			throw new CusDAOException("开户事务时出现异常",e);
		}
	}
	
	/** 提交事务并关闭连接 */
	public void commitAndClose() throws CusDAOException{
		try {
			conn.commit(); //提交事务
		} catch (SQLException e) {
			throw new CusDAOException("提交事务时出现异常",e);
		}finally{
			CusDbUtils.close(conn);
		}
	}
	
	/** 回滚并关闭连接 */
	public void rollbackAndClose()throws CusDAOException{
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new CusDAOException("回滚事务时出现异常",e);
		}finally{
			CusDbUtils.close(conn);
		}
	}
}
