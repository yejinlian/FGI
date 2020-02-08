/********************************************
 * 受控仪表管理Service层的实现
 *
 * @author zwq
 * @create 2018-06-03 16:06
 *********************************************/

package deepthinking.fgi.service.impl;


import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TInstrument;
import deepthinking.fgi.dto.frontend.TInstrumentDto;
import deepthinking.fgi.service.InstrumentMService;

@Service("instrumentMService")
public class InstrumentMServiceImpl extends BaseServiceImpl<TInstrument,String> implements InstrumentMService{
    public PageInfo<TInstrument> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return this.pageFind("selectAllInstruments",pageNum,pageSize,parameter);
    }

	@Override
	public PageInfo<TInstrumentDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFindModel("selectAllInstruments",pageNum,pageSize,parameter);
	}
}
