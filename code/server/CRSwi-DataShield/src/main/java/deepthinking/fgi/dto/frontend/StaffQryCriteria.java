/********************************************
 * 人员权限查询条件数据结构
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("人员权限查询条件数据结构")
public class StaffQryCriteria {
	@ApiModelProperty("员工名称")
	private String staffName;
	@ApiModelProperty("所属单位名称")
	private String orgnizationName;
	
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	
	
	
	

	
	
	
	

}
