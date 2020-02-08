/********************************************
 * 前端请求注册配对的数据结构
 *
 * @author zwq
 * @create 2018-06-03 19:57
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("前端请求注册配对的数据结构")
public class ReqRegDevDto {
    @ApiModelProperty("受控防篡改设备串号")
    private String serialNum;
    @ApiModelProperty("受控防篡改设备资产编号")
    private String deviceID;

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
