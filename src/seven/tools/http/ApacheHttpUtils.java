package seven.tools.http;

import java.io.File;
import java.io.InputStream;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class ApacheHttpUtils {
	private static Logger logger = Logger.getLogger(ApacheHttpUtils.class);

	public static String post(String url) {
		return post(url, "");
	}

	public static String post(String url, String data) {
		return httpPost(url, data);
	}

	public static String post(String url, InputStream instream) {
		try {
			HttpEntity entity = Request
					.Post(url)
					.bodyStream(instream,
							ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return ((entity != null) ? EntityUtils.toString(entity,Consts.UTF_8) : null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-- [post error] --" + e.getMessage() + "\n [post url]" + url);
		}
		return null;
	}

	public static String get(String url) {
		return httpGet(url);
	}

	private static String httpPost(String url, String data) {
		try {
			HttpEntity entity = Request
					.Post(url)
					.bodyString(data,
							ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return ((entity != null) ? EntityUtils.toString(entity,Consts.UTF_8) : null);
		} catch (Exception e) {	
			e.printStackTrace();
			logger.error("-- [post error] --" + e.getMessage() + "\n [post url]" + url + " data:" + data);
		}
		return null;
	}

	public static String postFile(String url, File file) {
		return postFile(url, null, file);
	}

	public static String postFile(String url, String name, File file) {
		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addBinaryBody(name, file).build();
			Request request = Request.Post(url);
			request.body(reqEntity);
			HttpEntity resEntity = request.execute().returnResponse()
					.getEntity();
			return ((resEntity != null) ? EntityUtils.toString(resEntity)
					: null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[postFile error]" + e.getMessage() + "\n post url:"
					+ url);
		}
		return null;
	}

	public static byte[] getFile(String url) {
		try {
			Request request = Request.Get(url);
			HttpEntity resEntity = request.execute().returnResponse()
					.getEntity();
			return EntityUtils.toByteArray(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[getFile error]" + e.getMessage() + "\n post url:"
					+ url);
		}
		return null;
	}

	private static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Get(url).execute().returnResponse()
					.getEntity();
			return ((entity != null) ? EntityUtils.toString(entity,Consts.UTF_8) : null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[get error]" + e.getMessage() + "\n get url:" + url);
		}
		return null;
	}
}