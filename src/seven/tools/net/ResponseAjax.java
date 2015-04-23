package seven.tools.net;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResponseAjax {

	private final static String RESULT = "result";
	
	private final static String PAGE = "ajax/ajax_result";
	
	public String responseAjax(HttpServletRequest request, Object obj) {
		String strJson = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		request.setAttribute(RESULT, strJson);
		return getAjaxResponsePage();
	}

	private String getAjaxResponsePage() {
		return PAGE;
	}
}
