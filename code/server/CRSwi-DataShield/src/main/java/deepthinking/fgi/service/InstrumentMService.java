/********************************************
 * 受控仪表设备管理Service层接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TInstrument;
import deepthinking.fgi.dto.frontend.TInstrumentDto;

public interface InstrumentMService extends BaseService<TInstrument,String>{

	PageInfo<TInstrumentDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
