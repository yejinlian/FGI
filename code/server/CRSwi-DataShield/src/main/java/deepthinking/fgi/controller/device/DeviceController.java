/********************************************
 * 设备通信API
 *
 * @author zwq
 * @create 2018-06-03 17:27
 *********************************************/

package deepthinking.fgi.controller.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deepthinking.fgi.dto.device.RepCmdDto;
import deepthinking.fgi.dto.device.RepDataUpDto;
import deepthinking.fgi.dto.device.ReqCmdDto;
import deepthinking.fgi.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*")
@Api(value="设备通信API")
@RestController
@RequestMapping("/device")
public class DeviceController {
    private static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    /**
     * 与设备通信-心跳接口
     * @author zwq
     * @create 2018-06-03
     * @param reqCmdDto 设备请求命令模型
     * @return 服务端回复设备的命令模型
     **/
    @PostMapping("/heartbeat")
    @ApiOperation(notes="与设备通信-心跳接口",value="接收设备心跳包",httpMethod="POST")
    public RepCmdDto heartbeat(@RequestBody  ReqCmdDto reqCmdDto){
        // 参数检测
        if(null!=reqCmdDto && !StringUtils.isEmpty(reqCmdDto.getCommand())){
            try{
                return deviceService.heartbeat(reqCmdDto);
            }catch (Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return null;
    }
    
    /**
     * 与设备通信-数据上载接口
     * @author zwq
     * @create 2018-06-06
     * @param reqCmdDto 设备请求命令模型
     * @return 服务端回复设备数据上载的命令模型
     **/
    @PostMapping("/data_up")
    @ApiOperation(notes="与设备通信-数据上载接口",value="接收数据上载请求",httpMethod="POST")
    public RepDataUpDto dataUp(@RequestBody  ReqCmdDto reqCmdDto){
        // 参数检测
        if(null!=reqCmdDto && !StringUtils.isEmpty(reqCmdDto.getCommand())){
            try{
                return deviceService.dataUp(reqCmdDto);
            }catch (Exception ex){
                logger.error(ex.getMessage());
            }
        }
        return null;
    }
}
