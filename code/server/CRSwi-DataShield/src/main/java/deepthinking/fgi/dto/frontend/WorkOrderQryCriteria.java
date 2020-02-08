/********************************************
 * 工单查询条件数据结构
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.dto.frontend;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工单查询条件数据结构")
public class WorkOrderQryCriteria {
	@ApiModelProperty("巡检员姓名")
    private String inspectorName;
	@ApiModelProperty("所属单位名称")
    private String orgnizationName;
	@ApiModelProperty("工单状态")
    private String orderStatus;
	@ApiModelProperty("开始出工日期")
	private Date startWorkDate;
	@ApiModelProperty("结束出工日期")
	private Date endWorkDate;
	
	
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public Date getEndWorkDate() {
		return endWorkDate;
	}
	public void setEndWorkDate(Date endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	
	

	
	
	
	

}
