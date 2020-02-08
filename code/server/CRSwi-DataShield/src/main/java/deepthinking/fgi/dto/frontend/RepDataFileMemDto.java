/********************************************
 * 描述备案数据文件的内存占用
 *
 * @author zwq
 * @create 2018-07-09
 *********************************************/
package deepthinking.fgi.dto.frontend;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务端返回的备案数据文件内存占用的数据结构")
public class RepDataFileMemDto {
	@ApiModelProperty("工单编码")
    private Integer orderCode;
	@ApiModelProperty("组织机构名称（单位名称）")
    private String orgnizationName;
	@ApiModelProperty("检测员姓名")
    private String inspectorName;
	@ApiModelProperty("出工日期")
    private Date workDate;
	@ApiModelProperty("报检单号")
    private String inspectionOrderNo;
	@ApiModelProperty("工单状态")
    private String orderStatus;
	@ApiModelProperty("占用空间")
    private Float memory;
	
	public Integer getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getInspectionOrderNo() {
		return inspectionOrderNo;
	}
	public void setInspectionOrderNo(String inspectionOrderNo) {
		this.inspectionOrderNo = inspectionOrderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Float getMemory() {
		return memory;
	}
	public void setMemory(Float memory) {
		this.memory = memory;
	}
	
	
	
	
	
}
