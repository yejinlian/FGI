/********************************************
 * 组织机构查询条件数据结构
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("组织机构查询条件数据结构")
public class OrgnizationQryCriteria {
	@ApiModelProperty("单位名称")
	private String orgnizationName;
	@ApiModelProperty("单位级别")
	private String orgnizationDegree;
	
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	public String getOrgnizationDegree() {
		return orgnizationDegree;
	}
	public void setOrgnizationDegree(String orgnizationDegree) {
		this.orgnizationDegree = orgnizationDegree;
	}

	
	
	
	

}
