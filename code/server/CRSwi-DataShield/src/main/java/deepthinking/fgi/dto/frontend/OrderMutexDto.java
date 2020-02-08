/********************************************
 * 工单互斥数据结构
 * 同一【单位+巡检员+出工日期】组合条件下，只能对应一台防篡改设备
 * @author zwq
 * @create 2018-06-24
 *********************************************/

package deepthinking.fgi.dto.frontend;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工单互斥验证数据结构：【单位+巡检员+出工日期】 对应 一台防篡改设备")
public class OrderMutexDto {
	@ApiModelProperty("组织机构编码")
    private Integer orgnizationCode;
	@ApiModelProperty("巡检员编码")
    private Integer inspectorCode;
	@ApiModelProperty("出工日期")
    private Date workDate;
	@ApiModelProperty("防篡改仪表编码")
    private String dsdeviceCode;
	
	public Integer getOrgnizationCode() {
		return orgnizationCode;
	}
	public void setOrgnizationCode(Integer orgnizationCode) {
		this.orgnizationCode = orgnizationCode;
	}
	public Integer getInspectorCode() {
		return inspectorCode;
	}
	public void setInspectorCode(Integer inspectorCode) {
		this.inspectorCode = inspectorCode;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getDsdeviceCode() {
		return dsdeviceCode;
	}
	public void setDsdeviceCode(String dsdeviceCode) {
		this.dsdeviceCode = dsdeviceCode;
	}
	
}
