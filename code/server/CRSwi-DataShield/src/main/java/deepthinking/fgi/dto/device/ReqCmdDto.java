/********************************************
 * 设备与服务端通信的命令模型
 *
 * @author zwq
 * @create 2018-06-03 18:43
 *********************************************/

package deepthinking.fgi.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备与服务端交互的命令模型")
public class ReqCmdDto {
    @ApiModelProperty("命令")
    private String command;
    @ApiModelProperty("负载")
    private Object dat;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getDat() {
        return dat;
    }

    public void setDat(Object dat) {
        this.dat = dat;
    }
}
