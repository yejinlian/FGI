/********************************************
 *  前端发送绑定验证请求数据结构
 *
 * @author zwq
 * @create 2018-06-03 21:13
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求绑定验证数据结构")
public class ReqVerBindDto {
	@ApiModelProperty("工单编号")
    private String ticketsNum; // 工单编号
	@ApiModelProperty("受控防篡改设备串号")
    private String serialNum; // 受控防篡改设备串号
	@ApiModelProperty("受控防篡改设备系统编号")
    private String deviceID; // 受控防篡改设备系统编号

    public String getTicketsNum() {
        return ticketsNum;
    }

    public void setTicketsNum(String ticketsNum) {
        this.ticketsNum = ticketsNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
