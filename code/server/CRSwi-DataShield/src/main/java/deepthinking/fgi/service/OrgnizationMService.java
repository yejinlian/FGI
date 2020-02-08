/********************************************
 * 组织结构管理的Service层接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import deepthinking.fgi.domain.TOrgnization;

public interface OrgnizationMService extends BaseService<TOrgnization,Integer>{

	List<Integer> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

}
