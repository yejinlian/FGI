/********************************************
 * 防篡改设备管理的外部API接口
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.controller.frontend;

import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.common.constants.DeviceCommand;
import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.dto.frontend.DsDeviceQryCriteria;
import deepthinking.fgi.dto.frontend.ReqRegDevDto;
import deepthinking.fgi.dto.frontend.TDatashieldDeviceDto;
import deepthinking.fgi.model.device.RepCmdLoad;
import deepthinking.fgi.model.device.RepRegDevModel;
import deepthinking.fgi.service.DSDeviceMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="防篡改设备管理API")
@RestController
@RequestMapping("/dsdeviceM")
public class DSDeviceMController {
	private static Logger logger = LoggerFactory.getLogger(DSDeviceMController.class);

	@Autowired
	private DSDeviceMService dSDeviceMService;

	/**
	 * “添加防篡改设备”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 添加成功或失败
	 **/
	@PostMapping("/dsdevice")
	@ApiOperation(notes="添加单台",value="添加防篡改设备",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "add", dataType = "TDatashieldDevice", name = "dSDevice", value = "防篡改设备数据结构", required = true)})
	public boolean addDSDevice(@RequestBody TDatashieldDevice dSDevice){
		// 需添加统一参数处理
		if(null!=dSDevice){
			try{
				return dSDeviceMService.insert(dSDevice)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	
	/**
	 * “分页查询所有防篡改设备”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 分页数据
	 **/
	@PostMapping("/dsdevice/pages")
	@ApiOperation(notes="查询所有",value="查询所有防篡改设备，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<TDatashieldDeviceDto> findAllDSDevices(int pageNum,int pageSize,@RequestBody DsDeviceQryCriteria dsDeviceQryCriteria){
		try{
			
			return dSDeviceMService.pageFindModel(pageNum,pageSize,dsDeviceQryCriteria);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	/**
	 * “对防篡改设备资产编号做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-24
	 * @return 是否唯一
	 **/
	@GetMapping("/dsdeviceNo/uniqueness")
	@ApiOperation(notes="防篡改设备资产编号唯一性检查",value="防篡改设备资产编号唯一性检查（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "dsdeviceCode", value = "防篡改设备资产编号", required = true)})
	public boolean checkDsdeviceNo(String dsdeviceCode){
		try{
			return (null!=dSDeviceMService.selectByPrimaryKey(dsdeviceCode))?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	/**
	 * “修改防篡改设备”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 更新成功或失败
	 **/
	@PutMapping("/dsdevice")
	@ApiOperation(notes="修改单台",value="更新防篡改设备",httpMethod="PUT")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "update", dataType = "TDatashieldDevice", name = "dSDevice", value = "防篡改设备数据结构", required = true)})
	public boolean updateDSDevice(@RequestBody TDatashieldDevice dSDevice){
		// 需添加统一参数处理
		if(null!=dSDevice){
			try{
				return dSDeviceMService.updateByPrimaryKeySelective(dSDevice)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除防篡改设备”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/dsdevice")
	@ApiOperation(notes="删除单台",value="删除防篡改设备",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "dsdeviceCode", value = "防篡改设备资产编号", required = true)})
	public boolean removeDSDevice(@RequestParam String dsdeviceCode){
		// 需添加统一参数处理
		if(null!=dsdeviceCode){
			try{
//				return dSDeviceMService.deleteByPrimaryKey(dsdeviceCode)>0?true:false;
				TDatashieldDevice dSDevice = new TDatashieldDevice();
				dSDevice.setDsdeviceCode(dsdeviceCode);
				dSDevice.setDeviceStatusCode("DS-OFF-01");
				return dSDeviceMService.updateByPrimaryKeySelective(dSDevice)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	
	/**
	 * “查询所有未配对的防篡改设备”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 设备串号列表
	 **/
	@RequestMapping("/dsdevice/unpaired_serialNos")
	@ApiOperation(notes="查询所有",value="查询所有未配对防篡改设备的串号",httpMethod="GET")
	public Set<String> findAllUnpairedSerialNos(){
		try{
			return dSDeviceMService.searchAllUnpairedSerialNos();
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	/**
	 * “防篡改设备注册配对”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 发送成功或失败
	 **/
	@PostMapping("/dsdevice/register_cmd")
	@ApiOperation(notes="发送命令",value="防篡改设备注册配对",httpMethod="POST")
	public boolean sendRegisterCmd(@RequestBody ReqRegDevDto reqRegDevDto){
		// 需添加统一参数处理
		if(null!=reqRegDevDto){
			try{
				
				String serialNum = reqRegDevDto.getSerialNum();
				if(null!=serialNum && DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
					// 发送注册配对命令
					RepCmdLoad repCmdLoad = new RepCmdLoad();
					repCmdLoad.setCommand(DeviceCommand.CMD_REG_DEV);
	                RepRegDevModel repRegDevModel = new RepRegDevModel();
	                repRegDevModel.setSerialNum(serialNum);
	                repRegDevModel.setCmcSerialNum(serialNum);
	                repRegDevModel.setDeviceID(reqRegDevDto.getDeviceID());
	                Random rand = new Random();
	                repRegDevModel.setCmcSerialNum(String.valueOf((rand.nextInt(998) + 1)));
	                repCmdLoad.setDat(repRegDevModel);
					
	                if(DataBus.g_ServerCmdData.containsKey(serialNum)){
	                    DataBus.g_ServerCmdData.remove(serialNum);
	                }
	                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);

	                return true;
				}else{
					// 也可能是其他操作导致
					return false;
					// 
				}
				
				// send register cmd，组合dsdeviceSerialNo
				
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
}
