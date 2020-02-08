/********************************************
 * 与设备交互的Service层
 *
 * @author zwq
 * @create 2018-06-03 18:54
 *********************************************/

package deepthinking.fgi.service;


import deepthinking.fgi.dto.device.RepCmdDto;
import deepthinking.fgi.dto.device.RepDataUpDto;
import deepthinking.fgi.dto.device.ReqCmdDto;

public interface DeviceService {
    RepCmdDto heartbeat(ReqCmdDto reqCmdDto);
    RepDataUpDto dataUp(ReqCmdDto reqCmdDto);
}
