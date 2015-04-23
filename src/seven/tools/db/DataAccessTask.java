package seven.tools.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Sql 任务接口
 * @author 
 *
 * @param <T>
 */
public interface DataAccessTask<T> {
	/**
	 * 获取sql执行语句
	 * @return
	 */
	String getSql();
	/**
	 * 设置SQL参数
	 * @param paramStr
	 */
	void setSql(String paramStr);
	/**
	 * 获取SQL参数
	 * @return
	 */
	Object[] getParameters();
	/**
	 * 设置多个SQL参数
	 * @param paramArrayOfObject
	 */
	void setParameters(Object[] paramArrayOfObject);
	/**
	 * 执行SQL语句
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	T execute(Connection conn) throws SQLException;
	/**
	 * 释放资源
	 * @throws SQLException
	 */
	void close() throws SQLException;
}
