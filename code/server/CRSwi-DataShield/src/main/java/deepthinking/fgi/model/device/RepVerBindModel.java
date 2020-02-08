/********************************************
 * 服务端发送验证绑定命令的数据结构
 *
 * @author zwq
 * @create 2018-06-03 23:18
 *********************************************/

package deepthinking.fgi.model.device;


public class RepVerBindModel {
    private String cmcSerialNum; // 服务器下发命令序号，用于识别发送端的身份
    private String ticketsNum;  // 工单编号
    private String serialNum;   // 受控防篡改设备串号
    private String deviceID;    // 受控防篡改设备系统编号

    public String getCmcSerialNum() {
        return cmcSerialNum;
    }

    public void setCmcSerialNum(String cmcSerialNum) {
        this.cmcSerialNum = cmcSerialNum;
    }

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
