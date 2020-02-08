/********************************************
 * 工单管理的Service层接口的实现
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.dao.mapper.TWorkordersMapper;
import deepthinking.fgi.domain.TWorkorders;
import deepthinking.fgi.dto.frontend.WorkorderDto;
import deepthinking.fgi.service.WorkOrdersMService;

@Service("workOrdersMService")
public class WorkOrdersMServiceImpl extends BaseServiceImpl<TWorkorders,Integer> implements WorkOrdersMService{

	@Autowired
	TWorkordersMapper workordersMapper;
	
	public PageInfo<WorkorderDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFindModel("selectAllWorkOrders", pageNum, pageSize, parameter);
	}

	@Override
	public PageInfo<TWorkorders> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFind("selectAllWorkOrders", pageNum, pageSize, parameter);
	}

	@Override
	public int updateByNonPrimarykeySelective(TWorkorders record, Object parameter) {
		return workordersMapper.updateByNonPrimarykeySelective(record, parameter);
	}

	@Override
	public List<TWorkorders> selectByNonPrimaryKey(Object queryCriteria) {
		// TODO Auto-generated method stub
		return workordersMapper.selectByNonPrimaryKey(queryCriteria);
	}




}
