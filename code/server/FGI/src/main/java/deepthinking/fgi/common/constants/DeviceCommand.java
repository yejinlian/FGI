/********************************************
 * 与设备交互命令常量表
 *
 * @author zwq
 * @create 2018-06-03 19:11
 *********************************************/

package deepthinking.fgi.common.constants;


public class DeviceCommand {
    // 心跳
    public final static String CMD_BEAT = "beating";
    // 注册配对
    public final static String CMD_REG_DEV = "registerDeviceID";
    // 绑定验证
    public final static String CMD_VER_BIND = "verifyBanding";
    // 绑定设备
    public final static String CMD_BIND = "bandDevice";
    // 解绑设备
    public final static String CMD_UN_BIND = "unbandDevice";
    // 获得日志
    public final static String CMD_UP_LOG = "getlog";
    // 日志上传结束
    public final static String CMD_UP_LOG_CMP = "getlogCmp";
    // 数据上载
    public final static String CMD_UP_DATA = "uploadData";
    // 数据上传结束
    public final static String CMD_UP_DATA_CMP = "uploadDataCmp";
    // 升级固件
    public final static String CMD_UP_FW = "upgradeFW";

}
