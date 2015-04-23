package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;

public class MapHandler implements ResultSetHandler<Map<String,Object>> {
	@Override
	public Map<String, Object> handle(ResultSet rs) throws SQLException {
		if(rs.next()){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(rs.getString("name"), rs.getObject("uid"));
			while(rs.next()){
				map.put(rs.getString("name"), rs.getObject("uid"));
			}
			return map;
		}
		return null;
	}
}
