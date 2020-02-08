/********************************************
 * 系统所有的数据总线
 *
 * @author zwq
 * @create 2018-06-03 20:08
 *********************************************/

package deepthinking.fgi.common;


import java.util.concurrent.ConcurrentHashMap;

import deepthinking.fgi.model.device.RepCmdLoad;
import deepthinking.fgi.model.frontend.ActiveMQDataModel;
import deepthinking.fgi.model.frontend.DsDeviceLogInfo;

public class DataBus {
    // 全局设备日志信息表，保存在线设备和其日志信息（传输状态，日志保存全Url）
    public static ConcurrentHashMap<String,DsDeviceLogInfo> g_DeviceReg_LogInFo_Map = new ConcurrentHashMap<String,DsDeviceLogInfo>();
    
    // 全局服务端指令数据表，出现在心跳消息的处理过程中
    // 包括注册配对、绑定验证（命令+数据结构dat+赋值时造数据）、设备解绑、获取日志、固件升级，以serialNum作为主键
    // 操作其他指令前都要判断是否已注册，未注册就返回error
    public static ConcurrentHashMap<String,RepCmdLoad> g_ServerCmdData = new ConcurrentHashMap<String,RepCmdLoad>();
    
    // 全局设备数据路径内存表，存放设备根据工单上传的数据文件信息
    // <巡检单号，<数据文件名称,数据文件路径>>
//    public static ConcurrentHashMap<String,ConcurrentHashMap<String,String>> g_DeviceDataPath = new  ConcurrentHashMap<String,ConcurrentHashMap<String,String>>();
    
    // for debug:暂时存储发送到MQ的数据
    public static ActiveMQDataModel g_ActiveMQData = new ActiveMQDataModel();
}
