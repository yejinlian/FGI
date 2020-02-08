/********************************************
 * 服务器回复绑定设备的数据结构
 *
 * @author zwq
 * @create 2018-06-03 21:51
 *********************************************/

package deepthinking.fgi.model.device;

import java.util.List;

public class RepBindModel {
    private String userID;        // 操作员编号
    private String deviceID;      // 受控防篡改设备系统编号
    private String staffID;       // 巡检员编号
    private long timeStamp;       // 服务器时间戳
    private List<TicketListModel> ticketList;   // 工单

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public List<TicketListModel> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketListModel> ticketList) {
        this.ticketList = ticketList;
    }

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
