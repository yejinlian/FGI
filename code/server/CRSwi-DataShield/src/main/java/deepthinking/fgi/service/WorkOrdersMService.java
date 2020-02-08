/********************************************
 * 工单管理Service层接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TWorkorders;
import deepthinking.fgi.dto.frontend.WorkorderDto;

public interface WorkOrdersMService extends BaseService<TWorkorders,Integer>{
	PageInfo<WorkorderDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	int updateByNonPrimarykeySelective(TWorkorders record, Object parameter);
	List<TWorkorders> selectByNonPrimaryKey(Object queryCriteria);
	
}
