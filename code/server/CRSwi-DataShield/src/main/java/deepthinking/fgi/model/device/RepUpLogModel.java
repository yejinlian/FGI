/********************************************
 * 服务端发起日志采集的数据结构
 *
 * @author zwq
 * @create 2018-06-03 22:53
 *********************************************/

package deepthinking.fgi.model.device;

public class RepUpLogModel {
    private String cmcSerialNum;    // 服务器下发命令序号，用于识别发送端的身份
    private String serialNum;       // 受控防篡改设备串号
    private String deviceID;        // 受控防篡改设备系统编号
    private String url;             // 日志上传位置，含用户名
    private String serialID;        // sync密码，※迷惑他人，所以用serialID

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String serialID) {
        this.serialID = serialID;
    }
}
