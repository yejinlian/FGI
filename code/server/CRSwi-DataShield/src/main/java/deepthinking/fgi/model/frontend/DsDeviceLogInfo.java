/********************************************
 * 防篡改设备日志信息模型
 *
 * @author zwq
 * @create 2018-07-14
 *********************************************/

package deepthinking.fgi.model.frontend;

public class DsDeviceLogInfo {
	// 日志传输状态
	private boolean logTransStatus;
	// 日志访问全Url路径
	private String logUrl;
	// 设备心跳旧时间戳
	private long oldTimestamp;
	// 设备心跳新时间戳
	private long newTimestamp;
	
	
	public DsDeviceLogInfo(){
		logTransStatus = false;
		logUrl = "";
		oldTimestamp=newTimestamp=0;
	}
	
	public boolean isLogTransStatus() {
		return logTransStatus;
	}
	public void setLogTransStatus(boolean logTransStatus) {
		this.logTransStatus = logTransStatus;
	}
	public String getLogUrl() {
		return logUrl;
	}
	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	public long getOldTimestamp() {
		return oldTimestamp;
	}

	public void setOldTimestamp(long oldTimestamp) {
		this.oldTimestamp = oldTimestamp;
	}

	public long getNewTimestamp() {
		return newTimestamp;
	}

	public void setNewTimestamp(long newTimestamp) {
		this.newTimestamp = newTimestamp;
	}
	
	
	
}
