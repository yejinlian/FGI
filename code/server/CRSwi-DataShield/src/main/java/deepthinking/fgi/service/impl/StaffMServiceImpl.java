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

import deepthinking.fgi.domain.TStaff;
import deepthinking.fgi.dto.frontend.TStaffDto;
import deepthinking.fgi.dto.frontend.VerfyLoginDto;
import deepthinking.fgi.service.StaffMService;

@Service("staffMService")
public class StaffMServiceImpl extends BaseServiceImpl<TStaff,Integer> implements StaffMService{
    public PageInfo<TStaff> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return this.pageFind("selectAllStaffs",pageNum,pageSize,parameter);
    }

	@Override
	public TStaffDto verfyLogin(VerfyLoginDto verfyLoginDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		List<TStaffDto> staffs = this.selectAllModelList("verfyLogin", verfyLoginDto);
		return staffs.size()>0?staffs.get(0):null;
	}

	@Override
	public PageInfo<TStaffDto> pageFindModel(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFindModel("selectAllStaffs", pageNum, pageSize, parameter);
	}

	@Override
	public List<String> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.selectAllModelList("checkStaffNumber",parameter);
	}
}
