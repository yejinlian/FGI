/********************************************
 * 工单管理的Service层接口的实现
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.dto.frontend.RepDataFileMemDto;
import deepthinking.fgi.dto.frontend.ReqDataFileMemDto;
import deepthinking.fgi.service.DataFileMService;

@Service("dataFileMService")
public class DataFileMServiceImpl extends BaseServiceImpl<TDsdataRecord,Integer> implements DataFileMService{


	@Override
	public PageInfo<TDsdataRecord> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFind("selectDataRecords", pageNum, pageSize, parameter);
	}

	@Override
	public PageInfo<RepDataFileMemDto> pageFindDataFileMem(int pageNum, int pageSize,
			ReqDataFileMemDto dataFileMemDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFindModel("selectDataFileMemRecords", pageNum, pageSize, dataFileMemDto);
	}




}
