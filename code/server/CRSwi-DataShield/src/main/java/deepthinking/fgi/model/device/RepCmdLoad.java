/********************************************
 * 服务端与设备交互命令的负载数据
 *
 * @author zwq
 * @create 2018-06-03 18:35
 *********************************************/

package deepthinking.fgi.model.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务端与设备交互命令的负载数据")
public class RepCmdLoad {
    @ApiModelProperty("命令")
    private String command;
    @ApiModelProperty("数据域")
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
