/********************************************
 * 回复数据比对请求的DTO
 *
 * @author zwq
 * @create 2018-06-20
 *********************************************/

package deepthinking.fgi.dto.frontend;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据对比结果数据结构")
public class RepDataCmpDto {
	@ApiModelProperty("对比结果标识（0：不一致，1：一致，-1：异常）")
	int rc;
	@ApiModelProperty("异常信息")
	String errorMsg;
	@ApiModelProperty("对比不一致详情")
	Map<String,String> dataCmpRlt;
	
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Map<String, String> getDataCmpRlt() {
		return dataCmpRlt;
	}
	public void setDataCmpRlt(Map<String, String> dataCmpRlt) {
		this.dataCmpRlt = dataCmpRlt;
	}
	
	
}
