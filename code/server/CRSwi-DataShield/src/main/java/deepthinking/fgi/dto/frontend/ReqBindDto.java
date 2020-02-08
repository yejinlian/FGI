/********************************************
 * 前端请求绑定命令的数据结构
 *
 * @author zwq
 * @create 2018-06-04 0:33
 *********************************************/

package deepthinking.fgi.dto.frontend;


import java.util.List;

import deepthinking.fgi.model.device.TicketListModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求绑定（工单签发）数据结构")
public class ReqBindDto {
	@ApiModelProperty("操作员编号")
    private String userID;        // 操作员编号
	@ApiModelProperty("受控防篡改设备系统编号")
    private String deviceID;      // 受控防篡改设备系统编号
	@ApiModelProperty("受控防篡改设备串号")
    private String serialNum; // 受控防篡改设备串号    
	@ApiModelProperty("UnBanded:解绑/Banded:绑定")
    private String bandStatus;    // UnBanded:解绑/Banded:绑定
	@ApiModelProperty("巡检员编号")
    private String staffID;       // 巡检员编号
	@ApiModelProperty("工单")
    private List<TicketListModel> ticketList;   // 工单

	
    public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

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

    public String getBandStatus() {
        return bandStatus;
    }

    public void setBandStatus(String bandStatus) {
        this.bandStatus = bandStatus;
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
}
