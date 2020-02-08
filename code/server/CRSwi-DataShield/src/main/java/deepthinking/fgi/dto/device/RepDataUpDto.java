package deepthinking.fgi.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务端与设备数据上载交互的命令模型")
public class RepDataUpDto {
    @ApiModelProperty("执行结果")
    private int rc;
    @ApiModelProperty("出错码")
    private String errCode;
    @ApiModelProperty("负载")
    private Object dat;
    
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
	public Object getDat() {
		return dat;
	}
	public void setDat(Object dat) {
		this.dat = dat;
	}
    
    
}
