package seven.tools.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 *  数据库工具类
 *  可以根据classpath下配置文件jdbc.properties中配置的参数来获取数据库连接并绑定到当前线程上
 *  可以获取JDBC的事务管理器
 * @author 
 * @version 
 */
public class CusDbUtils {
	private static Properties prop = new Properties();
	/** 数据源 */
	private static DataSource ds = null; 
	
	//用来把Connection绑定到当前线程上的变量
	private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		/*try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("在classpath下没有找到jdbc.properties文件");
		}*/
		
		//使用C3P0连接池技术
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			BoneCPDataSource dataSource=new BoneCPDataSource();
	        dataSource.setUsername("");
	        dataSource.setPassword("1234");
	        dataSource.setJdbcUrl("jdbc:mysql://192.168.1.99:3306/?useUnicode=true&characterEncoding=UTF-8");
	        dataSource.setMaxConnectionsPerPartition(10);
	        dataSource.setMinConnectionsPerPartition(5);
	        dataSource.setAcquireIncrement(5);
	        dataSource.setReleaseHelperThreads(3);
			ds = dataSource;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private CusDbUtils(){}
	
	/**
	 * 根据数据库的默认连接参数获取数据库的Connection对象，并绑定到当前线程上
	 * @return 成功，返回Connection对象，否则返回null
	 */
	public static synchronized Connection getConnection(){
		Connection conn = tl.get(); //先从当前线程上取出连接实例
		
		if(null == conn){ //如果当前线程上没有Connection的实例 
			try {
				conn = ds.getConnection(); // 从连接池中取出一个连接实例 
				tl.set(conn);  //把它绑定到当前线程上
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 获取事务管理器
	 * @return 事务管理实例
	 */
	public static synchronized CusTransactionManager getTranManager(){
		return new CusTransactionManager(getConnection());
	}
	
	/**
	 * 关闭数据库连接，并卸装线程绑定
	 * @param conn 要关闭数据库连接实例
	 * @throws DaoException 
	 */
	public static void close(Connection conn) throws CusDAOException{
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				throw new CusDAOException("关闭连接时出现异常",e);
			} finally {
			        tl.remove(); //卸装线程绑定
			}
		}
	}
	
}

