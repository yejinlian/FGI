/********************************************
 * 基础数据Service层接口的实现
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDictionaryValue;
import deepthinking.fgi.model.DictionaryModel;
import deepthinking.fgi.service.BaseDataService;

@Service("baseDataService")
public class BaseDataServiceImpl extends BaseServiceImpl<TDictionaryValue,String> implements BaseDataService{

	public PageInfo<TDictionaryValue> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFind("selectAllDeviceTypes", pageNum, pageSize, parameter);
	}

	/**
	 * 获取所有字典的数据模型
	 */
    public Map<String,Map<String,String>> getAllDics() 
    		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	 Map<String,Map<String,String>> dicMap = null;
		 
    	 List<DictionaryModel> dicModels = this.selectAllModelList("selectAllDics",null);
		 
		 if(null!=dicModels){
			 dicMap = new HashMap<String,Map<String,String>>();
			 for(DictionaryModel dicModel :dicModels){ 
				 String dicKey = dicModel.getDicCode()+"*"+dicModel.getDicName();
				 String dicValueKey = dicModel.getDicValueCode();
				 
				 if(dicMap.containsKey(dicKey)){
					 if(dicMap.get(dicKey).containsKey(dicValueKey)){
						 dicMap.get(dicKey).replace(dicValueKey, dicModel.getDicValueName());
					 }else{
						 dicMap.get(dicKey).put(dicValueKey, dicModel.getDicValueName());
					 }
				 }else{
					 Map<String,String> dicValueMap = new HashMap<String,String>();
					 dicValueMap.put(dicValueKey, dicModel.getDicValueName());
					 dicMap.put(dicKey, dicValueMap);
				 }
			 }
		 }
		 
		 return dicMap;
    }
    
}
