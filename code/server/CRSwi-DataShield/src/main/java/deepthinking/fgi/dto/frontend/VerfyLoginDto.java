

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录验证数据结构")
public class VerfyLoginDto {
	
	@ApiModelProperty("员工名称（必填）")
	private String staffName;
	@ApiModelProperty("员工工号（必填）")
	private String staffNumber;
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	
	
}
