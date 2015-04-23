package seven.tools.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResponseJson {
	
	private final static Logger logger = Logger.getLogger(ResponseJson.class);
	
	private final static String CHARACTER_ENCOD = "UTF-8";
	
	private final static String CONTENT_LENGTH = "Content-Length";
	
	protected String responseJson(HttpServletResponse response, Object obj, String description) {
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
				String strJson = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
				if(strJson != null) {
					response.setCharacterEncoding(CHARACTER_ENCOD);
					response.setHeader(CONTENT_LENGTH, Integer.toString(strJson.getBytes(CHARACTER_ENCOD).length));
				}
				logger.info(description + " Json: " + strJson);
				pw.print(strJson);
				pw.flush();
				pw.close();
			} catch(IOException e) {
				logger.error("ResponseJson.error:" + e.getMessage(),e);
			} finally {
				pw.flush();
				pw.close();
			}
			return null;
		}
}

