package seven.tools.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
	/**
	 * 处理结果值
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	T handle(ResultSet rs) throws SQLException;
}
