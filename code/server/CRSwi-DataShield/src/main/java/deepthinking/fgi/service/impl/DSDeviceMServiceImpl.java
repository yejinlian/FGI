/********************************************
 * 防篡改设备管理的Service层接口的实现
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.dto.frontend.DsDeviceQryCriteria;
import deepthinking.fgi.dto.frontend.TDatashieldDeviceDto;
import deepthinking.fgi.service.DSDeviceMService;

@Service("dSDeviceMService")
public class DSDeviceMServiceImpl extends BaseServiceImpl<TDatashieldDevice,String> implements DSDeviceMService{
	private static Logger logger = LoggerFactory.getLogger(DSDeviceMServiceImpl.class);
	
	@Override
	public PageInfo<TDatashieldDevice> pageFind(int pageNum, int pageSize, Object parameter)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFind("selectAllDSDevices", pageNum, pageSize, parameter);
	}

	/**
	 * 查询所有未配对的防篡改设备序列号
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Override
	public Set<String> searchAllUnpairedSerialNos() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// 
		Set<String> serialNos = new HashSet<String>();
		// 只返回未配对的设备序列号
		for(String serialNo:DataBus.g_DeviceReg_LogInFo_Map.keySet()){
			
			HashMap<String,String> dsSerialNoMap = new HashMap<String,String>();
			dsSerialNoMap.put("dsdeviceSerialNo", serialNo);
			try{
				// 操作数据库，判断是否配对
				List<TDatashieldDevice> dsDevices = this.selectAllModel(dsSerialNoMap);
				if(null!=dsDevices && dsDevices.size()>0){
					if(dsDevices.get(0).getDeviceStatusCode().equals("DS-OFF-01")){
						serialNos.add(serialNo);
					}else{
						continue;
					}
				}else{
					serialNos.add(serialNo);
				}
			}catch(Exception ex){
				logger.error(ex.getMessage());
				serialNos.add(serialNo);
			}

		}

		return serialNos;
	}

	@Override
	public List<TDatashieldDevice> selectAllModel(Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.selectAllModelList("selectDsDeviceLogs",parameter);
	}

	@Override
	public PageInfo<TDatashieldDeviceDto> pageFindModel(int pageNum, int pageSize,
			DsDeviceQryCriteria dsDeviceQryCriteria) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return this.pageFindModel("selectAllDSDevices", pageNum, pageSize, dsDeviceQryCriteria);
	}

	@Override
	public boolean isDsDeviceAvailable(String serialNum) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		HashMap<String,String> dsSerialNoMap = new HashMap<String,String>();
		dsSerialNoMap.put("dsdeviceSerialNo", serialNum);
		List<TDatashieldDevice> dsDevices = this.selectAllModel(dsSerialNoMap);
		if(null!=dsDevices && dsDevices.size()>0){
			if(dsDevices.get(0).getDeviceStatusCode().equals("DS-IN-01")){
				return true;
			}
		}
		return false;
	}




}
