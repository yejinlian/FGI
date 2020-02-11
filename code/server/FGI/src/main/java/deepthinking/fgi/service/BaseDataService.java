/********************************************
 * 基础数据Service层
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import deepthinking.fgi.domain.TDictionaryValue;

public interface BaseDataService extends BaseService<TDictionaryValue,String>{
	public Map<String, Map<String, String>> getAllDics() 
    		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	
}

