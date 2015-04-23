package seven.tools.db;

/**
 * SQL执行方法接口
 * @author
 *
 */
public interface DataAccessor {

	<T,P> T doQuery();
}
