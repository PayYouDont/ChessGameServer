
package com.payudon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/** 
* @ClassName: JsonUtil 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月19日 上午11:06:18 
*  
*/
public class JsonUtil {
	//private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}
	
	public static <T> T  parseObject(String json,Class<T> clazz) {
		return JSONObject.parseObject(json, clazz);
	}
}
