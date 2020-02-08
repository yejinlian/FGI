/********************************************
 * 组织结构管理的Service层接口的实现
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TOrgnization;
import deepthinking.fgi.service.OrgnizationMService;

@Service("orgnizationMService")
public class OrgnizationMServiceImpl extends BaseServiceImpl<TOrgnization,Integer> implements OrgnizationMService{

	public PageInfo<TOrgnization> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFind("selectAllOrgnizations", pageNum, pageSize, parameter);
	}


	public List<Integer> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.selectAllModelList("checkOrgnizationName",parameter);
	}

}
