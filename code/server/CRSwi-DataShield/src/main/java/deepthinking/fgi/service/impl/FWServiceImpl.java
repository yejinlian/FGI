/********************************************
 * 人员权限管理Service层的实现
 *
 * @author zwq
 * @create 2018-06-03 16:06
 *********************************************/

package deepthinking.fgi.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TFirmware;
import deepthinking.fgi.service.FWService;

@Service("fWService")
public class FWServiceImpl extends BaseServiceImpl<TFirmware,Integer> implements FWService{

	@Override
	public PageInfo<TFirmware> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<TFirmware> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.selectAllModelList("selectFWs",parameter);
	}



}
