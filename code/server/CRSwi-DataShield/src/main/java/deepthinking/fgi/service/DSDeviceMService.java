/********************************************
 * 防篡改设备管理的Service层接口
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.dto.frontend.DsDeviceQryCriteria;
import deepthinking.fgi.dto.frontend.TDatashieldDeviceDto;

public interface DSDeviceMService extends BaseService<TDatashieldDevice,String>{
	
	Set<String> searchAllUnpairedSerialNos() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	List<TDatashieldDevice> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	PageInfo<TDatashieldDeviceDto> pageFindModel(int pageNum, int pageSize, DsDeviceQryCriteria dsDeviceQryCriteria) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	boolean isDsDeviceAvailable(String serialNum) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
