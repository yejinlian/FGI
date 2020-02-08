/********************************************
 * 服务端与设备交互的命令模型
 *
 * @author zwq
 * @create 2018-06-03 18:24
 *********************************************/

package deepthinking.fgi.dto.device;

import deepthinking.fgi.model.device.RepCmdLoad;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务端与设备交互的命令模型")
public class RepCmdDto {
    @ApiModelProperty("执行结果")
    private int rc;
    @ApiModelProperty("出错码")
    private String errCode;
    @ApiModelProperty("负载")
    private RepCmdLoad dat;

    public int getRc() {
        return rc;
    }
    public void setRc(int rc) {
        this.rc = rc;
    }
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public RepCmdLoad getDat() {
        return dat;
    }

    public void setDat(RepCmdLoad dat) {
        this.dat = dat;
    }
}
