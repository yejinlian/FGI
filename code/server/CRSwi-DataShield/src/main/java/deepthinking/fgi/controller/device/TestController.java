/********************************************
 * 测试接口，模拟系统真实运行环境，用于服务端命令的发送
 *
 * @author zwq
 * @create 2018-06-03 23:07
 *********************************************/

package deepthinking.fgi.controller.device;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.common.constants.DeviceCommand;
import deepthinking.fgi.model.device.RepBindModel;
import deepthinking.fgi.model.device.RepCmdLoad;
import deepthinking.fgi.model.device.RepRegDevModel;
import deepthinking.fgi.model.device.RepUnBindModel;
import deepthinking.fgi.model.device.RepUpFWModel;
import deepthinking.fgi.model.device.RepUpLogDto;
import deepthinking.fgi.model.device.RepVerBindModel;
import deepthinking.fgi.model.device.TicketListModel;
import deepthinking.fgi.model.frontend.ActiveMQDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="测试接口，模拟系统真实运行环境，用于服务端命令的发送")
@RestController
@RequestMapping("/test")
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    /**
     * “模拟命令发送测试”接口
     * @author zwq
     * @create 2018-06-02
     * @return 发送成功与否
     **/
    @RequestMapping("/cmd")
    @ApiOperation(notes="模拟命令发送测试”接口",value="",httpMethod="GET")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "cmd"
            , value = "1:registerDeviceID,2:verifyBanding,3:bandDevice,4:unbandDevice,5:getlog,6:upgradeFW", required = true)
    ,@ApiImplicitParam(paramType = "query", dataType = "String", name = "deviceID"
            , value = "防篡改设备编号", required = true)
    ,@ApiImplicitParam(paramType = "query", dataType = "String", name = "serialNum"
            , value = "防篡改设备串号", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "String", name = "staffID"
            , value = "员工编号", required = true)})
    public boolean sendCmd(int cmd,String deviceID,String serialNum,String staffID){
        RepCmdLoad repCmdLoad = new RepCmdLoad();
        try{
            switch(cmd){
		        case 1:
		            {
		                repCmdLoad.setCommand(DeviceCommand.CMD_REG_DEV);
		                RepRegDevModel repRegDevModel = new RepRegDevModel();
		                repRegDevModel.setSerialNum(serialNum);
		                repRegDevModel.setDeviceID(deviceID);
		                repRegDevModel.setCmcSerialNum("999");
		                repCmdLoad.setDat(repRegDevModel);
		            }
		            break;
                case 2:
                    {
                        repCmdLoad.setCommand(DeviceCommand.CMD_VER_BIND);
                        RepVerBindModel repVerBindModel = new RepVerBindModel();
                        repVerBindModel.setDeviceID(deviceID);
                        repVerBindModel.setSerialNum(serialNum);
                        repVerBindModel.setTicketsNum("TICKET-009");
                        repVerBindModel.setCmcSerialNum("999");
                        repCmdLoad.setDat(repVerBindModel);
                    }
                    break;
                case 3:
                	{
                        // 根据数据库bandStatus判断该设备是否已绑定
                		// 若操作失败，返回错误字符串
                		 // 若已绑定，不发送绑定指令
	                    repCmdLoad.setCommand(DeviceCommand.CMD_BIND);
	                    RepBindModel repBindModel = new RepBindModel();
	                    repBindModel.setDeviceID(deviceID);
	                    repBindModel.setStaffID(staffID);
	                    repBindModel.setUserID("adminIme111");
	                    List<TicketListModel> ticketList = new ArrayList<TicketListModel>();
	                    TicketListModel _reqBindModel = new TicketListModel();
	                    _reqBindModel.setTicketID("TICKET-009");
	                    _reqBindModel.setTicketDate("20180529");
	                    _reqBindModel.setLineID("005");
	                    _reqBindModel.setLineDirection("0");
	                    _reqBindModel.setMileNum("999");
	                    _reqBindModel.setInstrumentID("0009");
	                    _reqBindModel.setInstrumentName("TYPE-001");
	                    ticketList.add(_reqBindModel);
	                    repBindModel.setTicketList(ticketList);
	                    repCmdLoad.setDat(repBindModel);
                	}
                	break;
                case 4:
                    {
                        repCmdLoad.setCommand(DeviceCommand.CMD_UN_BIND);
                        RepUnBindModel repUnBindModel = new RepUnBindModel();
                        repUnBindModel.setCmcSerialNum("009");
                        repUnBindModel.setDeviceID(deviceID);
                        repUnBindModel.setSerialNum(serialNum);
                        repUnBindModel.setTicketsNum("TICKET-009");
                        repCmdLoad.setDat(repUnBindModel);
                    }
                    break;
                case 5:
                    {
                        repCmdLoad.setCommand(DeviceCommand.CMD_UP_LOG);
                        RepUpLogDto repUpLogModel = new RepUpLogDto();
                        repUpLogModel.setSerialNum(serialNum);
                        repUpLogModel.setDeviceID(deviceID);
                        repUpLogModel.setCmcSerialNum("002");
                        repUpLogModel.setSerialID("password");
                        repUpLogModel.setUrl("rsync@http://leng45.eicp.net::log");
                        repCmdLoad.setDat(repUpLogModel);
                    }
                    break;
                case 6:
                    {
                        repCmdLoad.setCommand(DeviceCommand.CMD_UP_FW);
                        RepUpFWModel repUpFWModel = new RepUpFWModel();
                        repUpFWModel.setFwVersion("100.005");
                        repUpFWModel.setFwMD5("rxvsajkfarr");
                        repUpFWModel.setFwSize("512");
                        repUpFWModel.setUrl("http://leng45.eicp.net:8080/DataShield/FW/newfw.zip");
                        repCmdLoad.setDat(repUpFWModel);
                        
                        for(String _serialNum : DataBus.g_ServerCmdData.keySet()){
                            if(DataBus.g_ServerCmdData.containsKey(_serialNum)){
                                DataBus.g_ServerCmdData.replace(_serialNum, repCmdLoad);
                            }
                        }
                    }
                    break;
                default:
                    return true;
            }

            if(DataBus.g_ServerCmdData.containsKey(serialNum)){
                DataBus.g_ServerCmdData.remove(serialNum);
            }
            DataBus.g_ServerCmdData.put(serialNum,repCmdLoad);
        }catch(Exception ex){
            // 需添加统一异常处理
            logger.error(ex.getMessage());
        }
        return true;
    }
    
    
    /**
     * “测试”接口，获取发送到MQ的数据
     * @author zwq
     * @create 2018-06-19
     * @return 发送到第三方MQ的报检单数据
     **/
    @RequestMapping("/mq")
    @ApiOperation(notes="模拟命令发送测试”接口",value="",httpMethod="GET")
    public ActiveMQDataModel getMQData(){
    	return DataBus.g_ActiveMQData;
    }
    
    /**
     * “测试”接口：参数校验
     * @author zwq
     * @create 2018-06-28
     * @return 请求参数检测
     **/
    @RequestMapping("/params/validation")
    @ApiOperation(notes="参数校验测试”接口",value="",httpMethod="GET")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "kpId"
    , value = "", required = true)})
    public String validation(@Min(message = "kpId必须大于等于0", value = 0) @RequestParam int kpId){
    	return "validation";
    }
}

