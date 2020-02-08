/********************************************
 * 受控仪表查询条件数据结构
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("受控仪表查询条件数据结构")
public class InstrumentQryCriteria {
	@ApiModelProperty("仪表类型编码")
    private String deviceTypeCode;
	@ApiModelProperty("仪表名称")
    private String instrumentName;
	@ApiModelProperty("所属单位名称")
    private String orgnizationName;
	
	
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	
	
	

	
	
	
	

}
