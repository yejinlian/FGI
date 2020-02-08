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

@ApiModel("备案数据文件内存占用请求的数据结构")
public class ReqDataFileMemDto {
	@ApiModelProperty("开始出工日期")
	private Date startWorkDate;
	@ApiModelProperty("结束出工日期")
	private Date endWorkDate;
	@ApiModelProperty("单位名称")
	private String orgnizationName;
	
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
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	
	
	
}
