/********************************************
 * 服务端注册命令的响应数据模型
 *
 * @author zwq
 * @create 2018-06-03 20:25
 *********************************************/

package deepthinking.fgi.model.device;

public class RepRegDevModel {

    // 服务器下发命令序号，用于识别发送端的身份
    private String cmcSerialNum;
    // 受控防篡改设备串号
    private String serialNum;
    // 受控防篡改设备系统编号
    private String deviceID;

    public String getCmcSerialNum() {
        return cmcSerialNum;
    }

    public void setCmcSerialNum(String cmcSerialNum) {
        this.cmcSerialNum = cmcSerialNum;
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
