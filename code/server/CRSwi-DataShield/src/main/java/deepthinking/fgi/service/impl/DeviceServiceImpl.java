/********************************************
 * 与设备交互的Service层的实现
 *
 * @author zwq
 * @create 2018-06-03 18:55
 *********************************************/

package deepthinking.fgi.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.common.constants.CmdRlt;
import deepthinking.fgi.common.constants.DeviceCommand;
import deepthinking.fgi.config.FilePathConfiguration;
import deepthinking.fgi.dao.mapper.TWorkordersMapper;
import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.domain.TFirmware;
import deepthinking.fgi.domain.TInstrument;
import deepthinking.fgi.domain.TWorkorders;
import deepthinking.fgi.dto.device.RepCmdDto;
import deepthinking.fgi.dto.device.RepDataUpDto;
import deepthinking.fgi.dto.device.ReqBeetDto;
import deepthinking.fgi.dto.device.ReqCmdDto;
import deepthinking.fgi.dto.frontend.WorkorderDto;
import deepthinking.fgi.model.device.RepBindModel;
import deepthinking.fgi.model.device.RepCmdLoad;
import deepthinking.fgi.model.device.RepRegDevModel;
import deepthinking.fgi.model.device.RepUnBindModel;
import deepthinking.fgi.model.device.RepUpDataModel;
import deepthinking.fgi.model.device.RepUpFWModel;
import deepthinking.fgi.model.device.ReqUpDataCmpModel;
import deepthinking.fgi.model.device.ReqUpDataModel;
import deepthinking.fgi.model.device.ReqUpLogCmdModel;
import deepthinking.fgi.model.device.TicketListModel;
import deepthinking.fgi.model.frontend.ActiveMQDataModel;
import deepthinking.fgi.model.frontend.DsDeviceLogInfo;
import deepthinking.fgi.service.DSDeviceMService;
import deepthinking.fgi.service.DataFileMService;
import deepthinking.fgi.service.DeviceService;
import deepthinking.fgi.service.FWService;
import deepthinking.fgi.service.InstrumentMService;
import deepthinking.fgi.service.WorkOrdersMService;
import deepthinking.fgi.util.amq.ActiveMQTopicPublisher;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private WorkOrdersMService workOrdersMService;
	@Autowired
	private TWorkordersMapper workordersMapper;
	@Autowired
	private InstrumentMService instrumentMService;
	@Autowired
	private DSDeviceMService dSDeviceMService;
	@Autowired
    private ActiveMQTopicPublisher activeMQTopicPublisher;
	@Autowired
	private DataFileMService dataFileMService;
	@Autowired
	private FWService fWService;
	
	
    public RepCmdDto heartbeat(ReqCmdDto reqCmdDto){
        /*
         * 初始化数据模型
         */
        RepCmdDto repCmdModel = new RepCmdDto();
        RepCmdLoad repCmdLoad = new RepCmdLoad();
        repCmdLoad.setCommand(reqCmdDto.getCommand());
        Object dat = null;

        /*
         * 响应设备不同的请求
         */
         switch (reqCmdDto.getCommand()){
             case DeviceCommand.CMD_BEAT:
                 ReqBeetDto reqBeetModel = JSONObject.parseObject(JSONObject.toJSONString(reqCmdDto.getDat()),ReqBeetDto.class);
                 dat = beating(repCmdLoad,reqBeetModel);
                 break;
             default:
            	 dat = reqCmdDto.getDat();
                 break;
         }


        /*
         * 设置回复命令参数
         */
        repCmdLoad.setDat(dat);
        if(dat instanceof String){
            repCmdModel.setRc(CmdRlt.CMD_FAILED);
            repCmdModel.setErrCode((String)dat);
        }else{
            repCmdModel.setRc(CmdRlt.CMD_SUCCESS);
        }
        repCmdModel.setDat(repCmdLoad);
        return repCmdModel;
    }


    public RepDataUpDto dataUp(ReqCmdDto reqCmdDto){
        /*
         * 初始化数据模型
         */
    	RepDataUpDto repCmdModel = new RepDataUpDto();
        Object dat = null;     
        
        /*
         * 响应设备不同的请求
         * url配置记录
         */
         switch (reqCmdDto.getCommand()){
             case DeviceCommand.CMD_UP_DATA: // 数据上载
                 {
                     // 查询，该设备是否配对
                     try{
                        ReqUpDataModel reqUpDataModel = JSONObject.parseObject(JSONObject.toJSONString(reqCmdDto.getDat()),ReqUpDataModel.class);
                     	if(null!=reqUpDataModel && null!=reqUpDataModel.getDeviceID()){
                            TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqUpDataModel.getDeviceID());
                     		if(null==datashieldDevice || datashieldDevice.getDsdeviceSerialNo().equals("N/A")){
                     			dat = "未查找到设备配对信息，请先配对入库";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-IN-01")){
                     			dat = "设备未绑定，请先绑定设备";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-OFF-01")){
                     			dat = "设备已报废，请重新配对入库";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-REPAIRE-02")){
                     			dat = "设备在维修，需在库可用";
                     			break;
                     		}
                     	}

                     }catch(Exception ex){
                     	dat = ex.getMessage();
                     	logger.error(ex.getMessage());
                     	break;
                     }
                	 
                	 // 处理
                     RepUpDataModel repUpDataModel = new RepUpDataModel();
                     repUpDataModel.setUrl(FilePathConfiguration.getDataFilePath());//"rsync@leng45.eicp.net::data");
                     repUpDataModel.setSerialID(FilePathConfiguration.getDataFilePwd());
                     dat = repUpDataModel;
                 }
                 break;
             case DeviceCommand.CMD_UP_DATA_CMP: // 数据上载结束
                 {
                	 try{
                		ReqUpDataCmpModel reqUpDataCmpModel = JSONObject.parseObject(JSONObject.toJSONString(reqCmdDto.getDat()),ReqUpDataCmpModel.class);
                      	if(null!=reqUpDataCmpModel && null!=reqUpDataCmpModel.getDeviceID()){
                            TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(reqUpDataCmpModel.getDeviceID());
                            if(null==datashieldDevice || datashieldDevice.getDsdeviceSerialNo().equals("N/A")){
                     			dat = "未查找到设备配对信息，请先配对入库";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-IN-01")){
                     			dat = "设备未绑定，请先绑定设备";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-OFF-01")){
                     			dat = "设备已报废，请重新配对入库";
                     			break;
                     		}
                     		if(null!=datashieldDevice && datashieldDevice.getDeviceStatusCode().equals("DS-REPAIRE-02")){
                     			dat = "设备在维修，需在库可用";
                     			break;
                     		}
                     	}
                		 // 巡检完成后，防篡改设备中的数据上传后台结束，此时将工单状态=>【数据备案】:T-DAT-04。这时才有数据一致性检查的可能
                		 TWorkorders record = new TWorkorders();
		                 record.setOrderStatus("T-DAT-04");
//		                 record.setDataUrl(FilePathConfiguration.getDataServerPath()+reqUpDataCmpModel.getUrl());
		                 workOrdersMService.updateByNonPrimarykeySelective(record,reqUpDataCmpModel.getTicketID());
                		
		                 
		                 // 保存上传的数据文件信息
		                 HashMap<String,Object> queryCriteria = new HashMap<String,Object>();
		             	 queryCriteria.put("inspection_order_no", reqUpDataCmpModel.getTicketID());
		             	 List<TWorkorders> workorders = workOrdersMService.selectByNonPrimaryKey(queryCriteria);
		             	 if(workorders.size()==1){
			                 TDsdataRecord dataRecord = new TDsdataRecord();
			                 dataRecord.setOrderCode(workorders.get(0).getOrderCode());
			                 dataRecord.setDatafileName(reqUpDataCmpModel.getDataFileName());
			         		 long lt = new Long(reqUpDataCmpModel.getTimeStamp())*1000;
			                 dataRecord.setDatafileTime(new Date(lt));
			                 Double dataFileSize = 0.0;
			                 try{
			                	 dataFileSize=(reqUpDataCmpModel.getDataFileSize()==null?0:Double.parseDouble(reqUpDataCmpModel.getDataFileSize()));
			                 }catch(Exception ex){
			                	 logger.error(ex.getMessage());
			                	 dataFileSize=0.0;
			                 }
			                 dataRecord.setDatafileSize(BigDecimal.valueOf(dataFileSize));
			                 dataRecord.setDataUrl(reqUpDataCmpModel.getUrl());
			                 dataFileMService.insertSelective(dataRecord);
		             	 }

		                 
		                 
		                 // 记录数据上传后返回的数据地址（工单巡检编号与 - 数据文件名称+数据存储URL的对应关系）
//	                	 if(DataBus.g_DeviceDataPath.containsKey(reqUpDataCmpModel.getTicketID())){
//	                		 DataBus.g_DeviceDataPath.get(reqUpDataCmpModel.getTicketID()).put(reqUpDataCmpModel.getDataFileName(), reqUpDataCmpModel.getUrl());
//	                	 }else{
//	                		 ConcurrentHashMap<String,String> dataPathMap = new ConcurrentHashMap<String,String>();
//	                		 dataPathMap.put(reqUpDataCmpModel.getDataFileName(), reqUpDataCmpModel.getUrl());
//	                    	 DataBus.g_DeviceDataPath.put(reqUpDataCmpModel.getTicketID(), dataPathMap);
//	                	 }
		                 
                	 }catch(Exception ex){
                		 logger.error(ex.getMessage());
                	 }
 
                	 
                	 try{
                		 ReqUpDataCmpModel reqUpDataCmpModel = JSONObject.parseObject(JSONObject.toJSONString(reqCmdDto.getDat()),ReqUpDataCmpModel.class);
                		// 发送给第三方队列
                    	 ActiveMQDataModel activeMQDataModel = new ActiveMQDataModel();
                    	 activeMQDataModel.setDeviceID(reqUpDataCmpModel.getDeviceID());
                    	 activeMQDataModel.setReportID(reqUpDataCmpModel.getTicketID());
                    	 activeMQDataModel.setInstrumentName(reqUpDataCmpModel.getInstrumentName());
                    	 activeMQDataModel.setTimeStamp(reqUpDataCmpModel.getTimeStamp());
                    	 activeMQDataModel.setLongitude(reqUpDataCmpModel.getLongitude());
                    	 activeMQDataModel.setLatitude(reqUpDataCmpModel.getLatitude());
                    	 activeMQDataModel.setUrl(FilePathConfiguration.getDataServerPath()+reqUpDataCmpModel.getUrl());
                    	 
                    	 // 根据工单巡检编号，查询工单的巡检员名称和单位名称
                    	 // 添加异常检测，保障数据能够发送到MQ
                    	 try{
                    		 HashMap<String,String> queryCriteria = new HashMap<String,String>();
                    		 queryCriteria.put("inspection_order_no", reqUpDataCmpModel.getTicketID());
                    		 List<WorkorderDto> workorders = workordersMapper.selectWorkOrderByInspectorCode(queryCriteria);
                    		 if(null!=workorders && workorders.size()>0){
                    			 activeMQDataModel.setStaffName(workorders.get(0).getInspectorName());
                    			 activeMQDataModel.setCompanyName(workorders.get(0).getOrgnizationName());
                    		 }
                    		 // 扫描压缩包，读取readme文件，解析时间戳和经纬度
                    	 }catch(Exception ex){
                    		 logger.error(ex.getMessage());
                    	 }
                    	 
                    	 DataBus.g_ActiveMQData = activeMQDataModel;
                    	 
                    	 activeMQTopicPublisher.publishMsg("radar_data_topic", JSONObject.toJSONString(activeMQDataModel));
                    	 
                	 }catch(Exception ex){
                		 logger.error(ex.getMessage());
                	 }
                	
                	 
                 }
                 break;
             case DeviceCommand.CMD_UP_LOG_CMP:    // 日志上传完毕
                 {
                	 
                	 // 配置和记录日志的url
                     // 修改日志传输状态
                	 ReqUpLogCmdModel reqUpLogCmdModel = JSONObject.parseObject(JSONObject.toJSONString(reqCmdDto.getDat()),ReqUpLogCmdModel.class);

                	 if(DataBus.g_DeviceReg_LogInFo_Map.containsKey(reqUpLogCmdModel.getSerialNum())){ 
                		 DsDeviceLogInfo dsDeviceLogInfo = DataBus.g_DeviceReg_LogInFo_Map.get(reqUpLogCmdModel.getSerialNum());
                		 dsDeviceLogInfo.setLogTransStatus(true);
                		 dsDeviceLogInfo.setLogUrl(FilePathConfiguration.getLogServerPath()+reqUpLogCmdModel.getUrl());
                		 DataBus.g_DeviceReg_LogInFo_Map.replace(reqUpLogCmdModel.getSerialNum(), dsDeviceLogInfo); 
                	 }else{
                		 DsDeviceLogInfo dsDeviceLogInfo = new DsDeviceLogInfo();
    	               	 dsDeviceLogInfo.setLogTransStatus(true);
    	               	 dsDeviceLogInfo.setLogUrl(FilePathConfiguration.getLogServerPath()+reqUpLogCmdModel.getUrl());
                		 DataBus.g_DeviceReg_LogInFo_Map.put(reqUpLogCmdModel.getSerialNum(), dsDeviceLogInfo); 
                	 }
                	 
                 }
                break;
             default:
                 break;
         }
         
//         dat = reqCmdDto.getDat();

        /*
         * 设置回复命令参数
         */
        if(dat instanceof String){
            repCmdModel.setRc(CmdRlt.CMD_FAILED);
            repCmdModel.setErrCode((String)dat);
        }else{
            repCmdModel.setRc(CmdRlt.CMD_SUCCESS);
        }
        repCmdModel.setDat(dat);
        return repCmdModel;
    }
    
    /**
     * 处理心跳包请求
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    private Object beating(RepCmdLoad repCmdLoad,ReqBeetDto reqBeetModel) {
    	
	    // 如果是心跳消息，内存中维护所有在线设备及其配对状态，系统定时清空在线数据结构
	    // 配对状态判断依据： 查询库表
    	if(null!=reqBeetModel.getSerialNum() && !DataBus.g_DeviceReg_LogInFo_Map.containsKey(reqBeetModel.getSerialNum())){
    		 DsDeviceLogInfo dsDeviceLogInfo = new DsDeviceLogInfo();
           	 dsDeviceLogInfo.setLogTransStatus(false);
           	 // 每次心跳更改new时间戳字段
           	dsDeviceLogInfo.setNewTimestamp(System.currentTimeMillis());
    		DataBus.g_DeviceReg_LogInFo_Map.put(reqBeetModel.getSerialNum(), dsDeviceLogInfo);
    	}
    	
    	// 若设备在库可用，且固件版本不一致则发送固件升级命令
    	try{
    		if(dSDeviceMService.isDsDeviceAvailable(reqBeetModel.getSerialNum())){
    			List<TFirmware> firmwares = fWService.selectAllModel(null);
    			if(firmwares.size()>0 && (!firmwares.get(0).getFirmwareVersion().equals(reqBeetModel.getFwVersion()))){
    				RepCmdLoad _repCmdLoad = new RepCmdLoad();
    				_repCmdLoad.setCommand(DeviceCommand.CMD_UP_FW);
	                RepUpFWModel repUpFWModel = new RepUpFWModel();
	                repUpFWModel.setFwVersion(firmwares.get(0).getFirmwareVersion());
	                repUpFWModel.setFwMD5(firmwares.get(0).getFirmwareMd5());
	                repUpFWModel.setFwSize(String.valueOf(firmwares.get(0).getFirmwareSize()));
	                repUpFWModel.setUrl(firmwares.get(0).getFirmwarePath());//"http://leng45.eicp.net:8080/DataShield/FW/test.zip");
	                _repCmdLoad.setDat(repUpFWModel);
	                if(DataBus.g_ServerCmdData.containsKey(reqBeetModel.getSerialNum())){
	                	DataBus.g_ServerCmdData.remove(reqBeetModel.getSerialNum());
	                }
	                DataBus.g_ServerCmdData.put(reqBeetModel.getSerialNum(),_repCmdLoad);
	                
	                
    			}
    		}
    	}catch(Exception ex){
    		logger.error(ex.getMessage());
    	}
    	
    	
        Object dat = null;
        // 从全局结构中查看是否有服务端指令触发

       if(null!=reqBeetModel 
    		   &&null!=reqBeetModel.getSerialNum()
    		   &&DataBus.g_ServerCmdData.containsKey(reqBeetModel.getSerialNum())){
           // 如果存在针对该设备的指令，则找到指令并构造心跳回复包
           RepCmdLoad cmd_load = DataBus.g_ServerCmdData.get(reqBeetModel.getSerialNum());
           repCmdLoad.setCommand(cmd_load.getCommand());
    	   dat = cmd_load.getDat();
    	   DataBus.g_ServerCmdData.remove(reqBeetModel.getSerialNum());

    	   // 根据取走不同的指令改变数据库工单和防篡改设备表的不同状态
           switch(cmd_load.getCommand()){
           	case DeviceCommand.CMD_REG_DEV:
//           		if(DataBus.g_DeviceRegTable.containsKey(reqBeetModel.getSerialNum())){
//           			DataBus.g_DeviceRegTable.replace(reqBeetModel.getSerialNum(),true);
//           		}
           		RepRegDevModel repRegDevModel = (RepRegDevModel)dat;
           		try{
	            	TDatashieldDevice datashieldDevice = new TDatashieldDevice();
	            	datashieldDevice.setDsdeviceCode(repRegDevModel.getDeviceID());
	            	datashieldDevice.setDsdeviceSerialNo(repRegDevModel.getSerialNum());
	            	dSDeviceMService.updateByPrimaryKeySelective(datashieldDevice);
           		}catch (Exception e) {
           			logger.error(e.getMessage());
				}
           		
           		break;
           	case DeviceCommand.CMD_BIND:
	           	{
	           		// 不影响命令的下发，此处应try....catch...，屏蔽数据库操作异常带来的影响
	           		try{
		            	RepBindModel bindModel = (RepBindModel)dat;
		            	for(TicketListModel listModel:bindModel.getTicketList()){
		            		// 更新工单表的状态为“绑定出库”:"T-OUT-03"
		                	TWorkorders record = new TWorkorders();
		                	record.setOrderStatus("T-OUT-03");
		                	workOrdersMService.updateByNonPrimarykeySelective(record,listModel.getTicketID());
		                	
		                	// 更新仪表状态：DS-OUT-01
		                	TInstrument instrument = new TInstrument();
		                	instrument.setInstrumentCode(listModel.getInstrumentID());
		                	instrument.setDeviceStatusCode("DS-OUT-01");
		                	instrumentMService.updateByPrimaryKeySelective(instrument);
		                }
		            	// 更新防篡改状态：DS-OUT-01
		            	TDatashieldDevice datashieldDevice = new TDatashieldDevice();
		            	datashieldDevice.setDsdeviceCode(bindModel.getDeviceID());
		            	datashieldDevice.setDeviceStatusCode("DS-OUT-01");
		            	dSDeviceMService.updateByPrimaryKeySelective(datashieldDevice);
	           		}catch(Exception ex){
	           			logger.error(ex.getMessage());
	           		}
	           	}
           		break;
           	case DeviceCommand.CMD_UP_DATA_CMP:
           		// 更新工单表的状态为“数据备案”:"T-DAT-04"
           		// 更新仪表状态：DS-OUT-01，更新防篡改状态：DS-OUT-01
           		break;
           	case DeviceCommand.CMD_UN_BIND:
	           	{
	           		try{
	               		RepUnBindModel unbindModel = (RepUnBindModel)dat;
	               		// 更新工单表的状态为“解绑入库”:"T-IN-05"
	               		TWorkorders record = new TWorkorders();
	                	record.setOrderStatus("T-IN-05");
	                	workOrdersMService.updateByNonPrimarykeySelective(record,unbindModel.getTicketsNum());
	               		// 更新仪表状态：DS-IN-01
	                	HashMap<String,Object> queryCriteria = new HashMap<String,Object>();
	                	queryCriteria.put("inspection_order_no", unbindModel.getTicketsNum());
	                	List<TWorkorders> workorders = workOrdersMService.selectByNonPrimaryKey(queryCriteria);
	                	for(TWorkorders _order:workorders){
	                    	TInstrument instrument = new TInstrument();
	                    	instrument.setInstrumentCode(_order.getInstrumentCode());
	                    	instrument.setDeviceStatusCode("DS-IN-01");
	                    	instrumentMService.updateByPrimaryKeySelective(instrument);
	                	}
	                	// 更新防篡改状态：DS-IN-01
	                	TDatashieldDevice datashieldDevice = new TDatashieldDevice();
	                	datashieldDevice.setDsdeviceCode(unbindModel.getDeviceID());
	                	datashieldDevice.setDeviceStatusCode("DS-IN-01");
	                	dSDeviceMService.updateByPrimaryKeySelective(datashieldDevice);
	           		}catch(Exception ex){
	           			logger.error(ex.getMessage());
	           		}
	           	}
           		break;
           		
           	default:
           		break;
           }
    	   
       }else{
            // 如果只是心跳请求
           // 返回心跳包信息
    	   dat = reqBeetModel;
       }

        return dat;

    }


}
