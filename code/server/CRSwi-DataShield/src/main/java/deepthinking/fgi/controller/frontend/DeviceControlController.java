/********************************************
 * 设备控制API
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.controller.frontend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.common.constants.DeviceCommand;
import deepthinking.fgi.config.FilePathConfiguration;
import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.domain.TFirmware;
import deepthinking.fgi.domain.TOrgnization;
import deepthinking.fgi.domain.TWorkorders;
import deepthinking.fgi.dto.frontend.DatashieldDeviceDto;
import deepthinking.fgi.dto.frontend.RepDataCmpDto;
import deepthinking.fgi.dto.frontend.ReqBindDto;
import deepthinking.fgi.dto.frontend.ReqConsistencyDto;
import deepthinking.fgi.dto.frontend.ReqUnBindDto;
import deepthinking.fgi.dto.frontend.ReqUpLogDto;
import deepthinking.fgi.dto.frontend.ReqVerBindDto;
import deepthinking.fgi.model.device.RepBindModel;
import deepthinking.fgi.model.device.RepCmdLoad;
import deepthinking.fgi.model.device.RepUnBindModel;
import deepthinking.fgi.model.device.RepUpFWModel;
import deepthinking.fgi.model.device.RepUpLogDto;
import deepthinking.fgi.model.device.RepVerBindModel;
import deepthinking.fgi.model.device.TicketListModel;
import deepthinking.fgi.model.frontend.DsDeviceLogInfo;
import deepthinking.fgi.service.DSDeviceMService;
import deepthinking.fgi.service.DataFileMService;
import deepthinking.fgi.service.FWService;
import deepthinking.fgi.service.OrgnizationMService;
import deepthinking.fgi.service.WorkOrdersMService;
import deepthinking.fgi.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="设备控制API")
@RestController
@RequestMapping("/device")
public class DeviceControlController {
    private static Logger logger = LoggerFactory.getLogger(DeviceControlController.class);

    @Autowired
    private DSDeviceMService dSDeviceMService;
	@Autowired
	private WorkOrdersMService workOrdersMService;
	@Autowired
	private FWService fWService;
	@Autowired
	private OrgnizationMService orgnizationMService;
	@Autowired
	private DataFileMService dataFileMService;
	
	/**
	 * “设备绑定”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 绑定成功或失败
	 **/
	@PostMapping("/Binding")
	@ApiOperation(notes="发送命令",value="防篡改设备绑定（工单签发）",httpMethod="POST")
	public boolean sendVerBindingCmd(@RequestBody ReqBindDto reqBindDto){
		// 需添加统一参数处理
		if(null!=reqBindDto){
			try{
				// 先判断设备是否已配对
				TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqBindDto.getDeviceID());
				if(null!=datashieldDevice && !datashieldDevice.getDsdeviceSerialNo().equals("N/A") && datashieldDevice.getDeviceStatusCode().equals("DS-IN-01")){
					// 发送命令，操作共享数据结构
					String serialNum = reqBindDto.getSerialNum();
//					if(null!=serialNum && DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
						RepCmdLoad repCmdLoad = new RepCmdLoad();
		                repCmdLoad.setCommand(DeviceCommand.CMD_BIND);
		                RepBindModel repBindModel = new RepBindModel();
		                repBindModel.setDeviceID(reqBindDto.getDeviceID());
		                repBindModel.setStaffID(reqBindDto.getStaffID());
		                repBindModel.setUserID(reqBindDto.getUserID());
		                repBindModel.setTicketList(reqBindDto.getTicketList());
		                repBindModel.setTimeStamp(System.currentTimeMillis()/1000);
		                repCmdLoad.setDat(repBindModel);
		                if(DataBus.g_ServerCmdData.containsKey(serialNum)){
		                    DataBus.g_ServerCmdData.remove(serialNum);
		                }
		                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
		                
		                // 【签发】按钮，触发【工单状态】 - >【签发】
		                
		                for(TicketListModel listModel:reqBindDto.getTicketList()){
		                	TWorkorders record = new TWorkorders();
		                	record.setOrderStatus("T-SIGHED-02");
		                	workOrdersMService.updateByNonPrimarykeySelective(record,listModel.getTicketID());
		                }
		                return true;
//					}else{
//						return false;
//					}
				}else{
					return false;
				}
				
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
    
	/**
	 * “设备绑定验证”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 验证成功或失败
	 **/
	@PostMapping("/verBinding")
	@ApiOperation(notes="发送命令",value="防篡改设备绑定验证",httpMethod="POST")
	public boolean sendVerBindingCmd(@RequestBody ReqVerBindDto reqVerBindDto){
		// 需添加统一参数处理
		if(null!=reqVerBindDto){
			try{
				// 先判断设备是否已配对
				TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqVerBindDto.getDeviceID());
				if(null==datashieldDevice || datashieldDevice.getDsdeviceSerialNo().equals("N/A")){
					return false;
				}
				// 发送命令，操作共享数据结构;
				String serialNum = reqVerBindDto.getSerialNum();
				if(null!=serialNum && DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
					RepCmdLoad repCmdLoad = new RepCmdLoad();
					repCmdLoad.setCommand(DeviceCommand.CMD_VER_BIND);
                    RepVerBindModel repVerBindModel = new RepVerBindModel();
                    repVerBindModel.setDeviceID(reqVerBindDto.getDeviceID());
                    repVerBindModel.setSerialNum(serialNum);
                    repVerBindModel.setTicketsNum(reqVerBindDto.getTicketsNum());
                    Random rand = new Random();
                    repVerBindModel.setCmcSerialNum(String.valueOf((rand.nextInt(998) + 1)));
                    repCmdLoad.setDat(repVerBindModel);
	                if(DataBus.g_ServerCmdData.containsKey(serialNum)){
	                    DataBus.g_ServerCmdData.remove(serialNum);
	                }
	                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
	                return true;
				}else{
					return false;
				}
				
				// 判断操作成功还是失败
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * “设备解绑”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 解绑成功或失败
	 **/
	@PostMapping("/unBinding")
	@ApiOperation(notes="发送命令",value="防篡改设备解绑",httpMethod="POST")
	public boolean sendUnBindingCmd(@RequestBody ReqUnBindDto reqUnBindDto){
		// 需添加统一参数处理
		if(null!=reqUnBindDto){
			try{
				// 先判断设备是否已配对
				TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqUnBindDto.getDeviceID());
				if(null==datashieldDevice || datashieldDevice.getDsdeviceSerialNo().equals("N/A")){
					return false;
				}
				// 发送命令，操作共享数据结构;
				String serialNum = reqUnBindDto.getSerialNum();
				if(null!=serialNum && DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
					RepCmdLoad repCmdLoad = new RepCmdLoad();
                    repCmdLoad.setCommand(DeviceCommand.CMD_UN_BIND);
                    RepUnBindModel repUnBindModel = new RepUnBindModel();
                    Random rand = new Random();
                    repUnBindModel.setCmcSerialNum(String.valueOf((rand.nextInt(998) + 1)));
                    repUnBindModel.setDeviceID(reqUnBindDto.getDeviceID());
                    repUnBindModel.setSerialNum(serialNum);
                    repUnBindModel.setTicketsNum(reqUnBindDto.getTicketsNum());
                    repCmdLoad.setDat(repUnBindModel);
	                if(DataBus.g_ServerCmdData.containsKey(serialNum)){
	                    DataBus.g_ServerCmdData.remove(serialNum);
	                }
	                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
	                return true;
				}else{
					return false;
				}
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * “数据一致性验证”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 解绑成功或失败
	 **/
	@PostMapping("/dataConsistency")
	@ApiOperation(notes="add",value="防篡改设备数据一致性验证",httpMethod="POST")
	public RepDataCmpDto verDataConsistency(@RequestBody ReqConsistencyDto reqConsistencyDto){
		// 需添加统一参数处理
		RepDataCmpDto repDataCmpDto = new RepDataCmpDto();
		repDataCmpDto.setRc(1);  // 默认为一致
		
		if(null!=reqConsistencyDto && null!=reqConsistencyDto.getInspectionOrderNo()){
			try{
				
				// 根据文件路径读取文件，计算Hash值，并比对
				// 获取云服务器上对应的文件
				// 数据库操作：根据巡检员编码查询巡检员工号
				
				Map<String,String> cmpRlt = new HashMap<String,String>();  // 定义保存比对详情的数据结构
				// 查找是否存在该文件
				TDsdataRecord dsRecord = dataFileMService.selectByPrimaryKey(reqConsistencyDto.getDatafileCode());
				if(null!=dsRecord){
//				if(true){//null!=dsRecord){
//					("E:\\zwq\\TIC0004_1531402958.tar.bz2")
//					String md5 = FileUtils.readGzipFile("D:\\YYT_CC\\001_1542549562.tar.bz2");
					String md5 = FileUtils.readGzipFile(FilePathConfiguration.getLocalDataFilePath()+dsRecord.getDataUrl());
					String[] md5Array = md5.trim().split("\n");
				
					
					for(int i=0;i<md5Array.length;i++){
						String[] _md5ArrayTmp = md5Array[i].trim().split(" ");
						for(int j=0;j<_md5ArrayTmp.length;j++){
							if(!reqConsistencyDto.getMd5_fileName().containsKey(_md5ArrayTmp[0])) continue;
							if(!_md5ArrayTmp[2].contains(reqConsistencyDto.getMd5_fileName().get(_md5ArrayTmp[0]))){
//								 notConsistencyList.add(_md5ArrayTmp[2]);
								repDataCmpDto.setRc(0);
								 cmpRlt.put(_md5ArrayTmp[2], "不一致");
							}else{
								cmpRlt.put(_md5ArrayTmp[2], "一致");
							}
						}
					}
				}else{
					repDataCmpDto.setRc(-1);
					repDataCmpDto.setErrorMsg("未收到该工单("+reqConsistencyDto.getInspectionOrderNo()+")对应设备发出的数据上载结束请求！数据文件不存在，文件编码： "+reqConsistencyDto.getDatafileCode());
					return repDataCmpDto;
				}
				
				// 判断缺项
				for(String clientMd5:reqConsistencyDto.getMd5_fileName().keySet()){
					boolean flag = false;
					for(String cmpFileName:cmpRlt.keySet()){
						if(cmpFileName.contains(reqConsistencyDto.getMd5_fileName().get(clientMd5))){
							flag = true;
						}
					}
					if(!flag){
						repDataCmpDto.setRc(0);
						cmpRlt.put(reqConsistencyDto.getMd5_fileName().get(clientMd5), "服务器不存在该文件");
					}
				}
				
				repDataCmpDto.setDataCmpRlt(cmpRlt);
				
				// 如果比对结果是：不一致
				if(0==repDataCmpDto.getRc()){
					 // 更新数据库字段为不一致
//           		 	TWorkorders record = new TWorkorders();
//	                 record.setDataConsistency(0);
//	                 workOrdersMService.updateByNonPrimarykeySelective(record,reqConsistencyDto.getInspectionOrderNo());
					TDsdataRecord record = new TDsdataRecord();
					record.setDatafileCode(reqConsistencyDto.getDatafileCode());
					record.setDataConsistency(0);
					dataFileMService.updateByPrimaryKeySelective(record);
					
				}else if(1==repDataCmpDto.getRc()){
					// 操作工单库表，改变工单数据一致性状态：一致
//	       		 	TWorkorders record = new TWorkorders();
//	                record.setDataConsistency(1);
//	                workOrdersMService.updateByNonPrimarykeySelective(record,reqConsistencyDto.getInspectionOrderNo());
					TDsdataRecord record = new TDsdataRecord();
					record.setDatafileCode(reqConsistencyDto.getDatafileCode());
					record.setDataConsistency(1);
					dataFileMService.updateByPrimaryKeySelective(record);
				}

			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
				repDataCmpDto.setRc(-1);
				repDataCmpDto.setErrorMsg("发生异常:"+ex.getMessage());
				return repDataCmpDto;
			}
		}else{
			repDataCmpDto.setRc(-1);
			repDataCmpDto.setErrorMsg("发生异常:请求参数异常");
			return repDataCmpDto;
		}
		return repDataCmpDto;
	}
	
	 /* “查询当前所有在线设备日志状态”接口
	 * 疑问？ 日志传输状态是否与工单绑定？是实时的吗？
	 * @author zwq
	 * @create 2018-06-06
	 * @return 防篡改设备日志状态数据结构
	 **/
	@RequestMapping("/log")
	@ApiOperation(notes="查询当前",value="查询当前所有在线设备日志状态",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "organizationCode", value = "单位编码", required = true)})
	public List<DatashieldDeviceDto> findAllLogStatusOnLine(int organizationCode){
		List<DatashieldDeviceDto> datashieldDeviceDtos = new ArrayList<DatashieldDeviceDto>();
		try{
			// 根据心跳探测到的在线设备，查询设备表，组织数据结构，暂时在内存中维护传输状态
			for(String dsdeviceSerialNo:DataBus.g_DeviceReg_LogInFo_Map.keySet()){
				HashMap<String,String> params = new HashMap<String,String>();
				params.put("dsdeviceSerialNo", dsdeviceSerialNo);
				List<TDatashieldDevice> datashieldDevice = dSDeviceMService.selectAllModel(params);
				if(null!=datashieldDevice){
					for(TDatashieldDevice _datashieldDevice:datashieldDevice){
						// 不是在库可用的设备不返回日志状态信息
//						if(!_datashieldDevice.getDeviceStatusCode().equals("DS-IN-01")) continue;
						// 不是本单位的不返回
						if(0!=organizationCode && organizationCode!=_datashieldDevice.getOrganizationCode()) continue;
						DatashieldDeviceDto datashieldDeviceDto = new DatashieldDeviceDto();
						
						TOrgnization orgnization = orgnizationMService.selectByPrimaryKey(_datashieldDevice.getOrganizationCode());
						if(null!=orgnization){
							datashieldDeviceDto.setOrgnizationName(orgnization.getOrgnizationName());
						}
						

						datashieldDeviceDto.setDsdeviceCode(_datashieldDevice.getDsdeviceCode());
						datashieldDeviceDto.setDsdeviceName(_datashieldDevice.getDsdeviceName());
						datashieldDeviceDto.setDsdeviceSerialNo(_datashieldDevice.getDsdeviceSerialNo());
						datashieldDeviceDto.setOrganizationCode(_datashieldDevice.getOrganizationCode());
						datashieldDeviceDto.setDeviceStatusCode(_datashieldDevice.getDeviceStatusCode());
						if(DataBus.g_DeviceReg_LogInFo_Map.containsKey(_datashieldDevice.getDsdeviceSerialNo())){
							datashieldDeviceDto.setLogTransPortStatus(DataBus.g_DeviceReg_LogInFo_Map.get(_datashieldDevice.getDsdeviceSerialNo()).isLogTransStatus()?"结束":"待处理");
							datashieldDeviceDto.setLogUrl(DataBus.g_DeviceReg_LogInFo_Map.get(_datashieldDevice.getDsdeviceSerialNo()).getLogUrl());
						}else{
							datashieldDeviceDto.setLogTransPortStatus("待处理");
							datashieldDeviceDto.setLogUrl("未收到设备“日志上传结束”请求");
						}
						
						datashieldDeviceDtos.add(datashieldDeviceDto);
					}
				}
			}
			
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return datashieldDeviceDtos;
	}
	
	/**
	 * “收取设备日志”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 收取成功或失败
	 **/
	@PostMapping("/upLog")
	@ApiOperation(notes="发送命令",value="防篡改设备日志采集",httpMethod="POST")
	public boolean sendUnBindingCmd(@RequestBody ReqUpLogDto reqUpLogDto){
		// 需添加统一参数处理
		if(null!=reqUpLogDto){
			try{
				// 先判断设备是否已配对，且状态正常
				TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqUpLogDto.getDeviceID());
				if(null==datashieldDevice 
						|| datashieldDevice.getDsdeviceSerialNo().equals("N/A") 
						|| datashieldDevice.getDeviceStatusCode().equals("DS-OFF-01")
						|| datashieldDevice.getDeviceStatusCode().equals("DS-REPAIRE-02")){
					return false;
				}
				// 发送命令，操作共享数据结构;
				String serialNum = reqUpLogDto.getSerialNum();
				if(null!=serialNum && DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
					RepCmdLoad repCmdLoad = new RepCmdLoad();
					repCmdLoad.setCommand(DeviceCommand.CMD_UP_LOG);
                    RepUpLogDto repUpLogModel = new RepUpLogDto();
                    repUpLogModel.setSerialNum(serialNum);
                    repUpLogModel.setDeviceID(reqUpLogDto.getDeviceID());
                    Random rand = new Random();
                    repUpLogModel.setCmcSerialNum(String.valueOf((rand.nextInt(998) + 1)));
                    repUpLogModel.setSerialID(FilePathConfiguration.getLogFilePwd());
                    repUpLogModel.setUrl(FilePathConfiguration.getLogFilePath());
                    repCmdLoad.setDat(repUpLogModel);
	                if(DataBus.g_ServerCmdData.containsKey(serialNum)){
	                    DataBus.g_ServerCmdData.remove(serialNum);
	                }
	                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
	                
	                // 发送收取日志命令后，改变日志传输状态
                    // 修改日志传输状态
	               	if(DataBus.g_DeviceReg_LogInFo_Map.containsKey(serialNum)){
	               		DsDeviceLogInfo dsDeviceLogInfo = DataBus.g_DeviceReg_LogInFo_Map.get(serialNum);
	               		dsDeviceLogInfo.setLogTransStatus(false);
	               		DataBus.g_DeviceReg_LogInFo_Map.replace(serialNum, dsDeviceLogInfo); 
	               	}else{
		                DsDeviceLogInfo dsDeviceLogInfo = new DsDeviceLogInfo();
	               		dsDeviceLogInfo.setLogTransStatus(false);
	               		DataBus.g_DeviceReg_LogInFo_Map.put(serialNum, dsDeviceLogInfo); 
	               	}
	                return true;
				}else{
					return false;
				}
				
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	@PostMapping(value="/FW/fileUpload",consumes = "multipart/*", headers="content-type=multipart/form-data")
	@ApiOperation(notes="固件文件上传",value="上传固件包",httpMethod="POST")
    public String fileUpload(@RequestParam("formData") MultipartFile file){
		
        if(file.isEmpty()){
            return "MultipartFile无效";
        }
        
        String fileName = file.getOriginalFilename();
        String filePath = FilePathConfiguration.getFwFilePath()+fileName;
        File dest = new File(filePath);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return FilePathConfiguration.getFwServerPath()+fileName;
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return "文件保存失败:"+e.getMessage();
        } catch (IOException e) {
        	logger.error(e.getMessage());
            return "文件保存失败:"+e.getMessage();
        }
    }
	
	/**
	 * “设备固件升级”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 升级成功或失败
	 **/
	@PutMapping("/FW")
	@ApiOperation(notes="发送命令",value="防篡改设备固件升级",httpMethod="PUT")
	public boolean sendFWCmd(@RequestBody TFirmware firmware){
		// 需添加统一参数处理
		if(null!=firmware){
			try{
				// 更新数据库
				if(fWService.updateByPrimaryKey(firmware)>0){
					// 发送升级命令，操作共享数据结构;
					RepCmdLoad repCmdLoad = new RepCmdLoad();
					repCmdLoad.setCommand(DeviceCommand.CMD_UP_FW);
	                RepUpFWModel repUpFWModel = new RepUpFWModel();
	                repUpFWModel.setFwVersion(firmware.getFirmwareVersion());
	                repUpFWModel.setFwMD5(firmware.getFirmwareMd5());
	                repUpFWModel.setFwSize(String.valueOf(firmware.getFirmwareSize()));
	                repUpFWModel.setUrl(firmware.getFirmwarePath());//"http://leng45.eicp.net:8080/DataShield/FW/test.zip");
	                repCmdLoad.setDat(repUpFWModel);
	                
	                for(String serialNum : DataBus.g_DeviceReg_LogInFo_Map.keySet()){
	                	
	                	// 只对【在库可用】的设备发送固件升级命令
	                	try{
	                		if(!dSDeviceMService.isDsDeviceAvailable(serialNum)){
	                			continue;
	                		}
	                	}catch(Exception ex){
	                		logger.error(ex.getMessage());
	                		continue;
	                	}
	                	if(DataBus.g_ServerCmdData.containsKey(serialNum)){
		                	DataBus.g_ServerCmdData.remove(serialNum);
		                }
		                DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
		                
	                }
	                return true;	
				}else{
					return false;
				}
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “查询设备固件信息”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 设备固件数据结构
	 **/
	@RequestMapping("/FW")
	@ApiOperation(notes="查询当前",value="查询当前设备的固件信息",httpMethod="GET")
	public TFirmware findCurrentFW(){
		try{
			// 查询固件信息表，返回数据
			List<TFirmware> firmwares = fWService.selectAllModel(null);
			return firmwares.size()>0?firmwares.get(0):null;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
}
