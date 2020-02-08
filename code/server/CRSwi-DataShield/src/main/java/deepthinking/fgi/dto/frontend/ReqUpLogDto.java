/********************************************
 * 前端发起日志采集的数据结构
 *
 * @author zwq
 * @create 2018-06-06 22:53
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求日志采集数据结构")
public class ReqUpLogDto {
	@ApiModelProperty("受控防篡改设备串号")
    private String serialNum;       // 受控防篡改设备串号
	@ApiModelProperty("受控防篡改设备系统编号")
    private String deviceID;        // 受控防篡改设备系统编号


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
