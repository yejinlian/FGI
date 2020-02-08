/********************************************
 * 人员权限管理Service层接口
 *
 * @author zwq
 * @create 2018-06-02
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TStaff;
import deepthinking.fgi.dto.frontend.TStaffDto;
import deepthinking.fgi.dto.frontend.VerfyLoginDto;

public interface StaffMService extends BaseService<TStaff,Integer>{
	TStaffDto verfyLogin(VerfyLoginDto verfyLoginDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	PageInfo<TStaffDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	List<String> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
