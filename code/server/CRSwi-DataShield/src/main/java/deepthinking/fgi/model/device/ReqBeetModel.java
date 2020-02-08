/********************************************
 * 设备请求的心跳包负载数据
 *
 * @author zwq
 * @create 2018-06-03 20:55
 *********************************************/

package deepthinking.fgi.model.device;


public class ReqBeetModel {

    private String userID; // 操作员编号,如没有则设为空
    private String serialNum; // 受控防篡改设备串号
    private String deviceID; // 受控防篡改设备系统编号，如没有则为空
    private String fwVersion; // 固件版本号
    private String staffID; // 巡检员编号,如没有则设为空
    private String longitude; // 位置:经度值,如没有则设为空
    private String latitude; // 位置:纬度值,如没有则设为空
    private String timeStamp; // 时间戳(精确到秒)

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
