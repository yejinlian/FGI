/********************************************
 * 服务器发送固件升级命令数据结构
 *
 * @author zwq
 * @create 2018-06-03 23:33
 *********************************************/

package deepthinking.fgi.model.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求固件升级数据结构")
public class RepUpFWModel {
	@ApiModelProperty("待升级固件版本号")
    private String fwVersion;   // 待升级固件版本号
	@ApiModelProperty("待升级固件大小")
    private String fwSize;      // 待升级固件大小
	@ApiModelProperty("待升级固件MD5")
    private String fwMD5;       // 待升级固件MD5
	@ApiModelProperty("待升级固件下载路径")
    private String url;         // 待升级固件下载路径

    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    public String getFwSize() {
        return fwSize;
    }

    public void setFwSize(String fwSize) {
        this.fwSize = fwSize;
    }

    public String getFwMD5() {
        return fwMD5;
    }

    public void setFwMD5(String fwMD5) {
        this.fwMD5 = fwMD5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
