/********************************************
 * 系统所有的数据总线
 *
 * @author zwq
 * @create 2018-06-03 20:08
 *********************************************/

package deepthinking.fgi.common;


import java.util.concurrent.ConcurrentHashMap;

import deepthinking.fgi.model.DsDeviceLogInfo;

public class DataBus {
    // 全局设备日志信息表，保存在线设备和其日志信息（传输状态，日志保存全Url）
    public static ConcurrentHashMap<String,DsDeviceLogInfo> g_DeviceReg_LogInFo_Map = new ConcurrentHashMap<String,DsDeviceLogInfo>();
    
}
