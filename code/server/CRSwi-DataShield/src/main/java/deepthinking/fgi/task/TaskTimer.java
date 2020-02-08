/********************************
 *  后台定时器，定时检测心跳包，30s内若设备时间戳未更新，则剔除
 *  在DataBus.g_DeviceReg_LogInFo_Map的DsDeviceLogInfo中增加old,new时间戳字段，检测时间戳是否变化
 *  每次心跳更改new字段，若检测到相等则剔除，不相等则更改old字段=new字段
 */

package deepthinking.fgi.task;

import java.util.Timer;
import java.util.TimerTask;

import deepthinking.fgi.common.DataBus;
import deepthinking.fgi.model.frontend.DsDeviceLogInfo;

public class TaskTimer {
	
	public static void startTask() {

		Timer timer = new Timer("checkBeetTask", true);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				for(String dsdeviceSerialNo:DataBus.g_DeviceReg_LogInFo_Map.keySet()){
					DsDeviceLogInfo dsDeviceLogInfo = DataBus.g_DeviceReg_LogInFo_Map.get(dsdeviceSerialNo);
					if(dsDeviceLogInfo.getOldTimestamp()==dsDeviceLogInfo.getNewTimestamp()){
						DataBus.g_DeviceReg_LogInFo_Map.remove(dsdeviceSerialNo);
					}else{
						dsDeviceLogInfo.setOldTimestamp(dsDeviceLogInfo.getNewTimestamp());
					}
				}
			}

		}, 5000, 30000);
	}
}
