/********************************************
 * 人员权限管理Service层接口
 *
 * @author zwq
 * @create 2018-06-02
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import deepthinking.fgi.domain.TFirmware;

public interface FWService extends BaseService<TFirmware,Integer>{

	List<TFirmware> selectAllModel(Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
}
