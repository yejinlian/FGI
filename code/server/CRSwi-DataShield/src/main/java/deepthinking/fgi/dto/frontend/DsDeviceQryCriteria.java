/********************************************
 * 防篡改设备管理的查询条件
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("防篡改设备查询条件数据结构")
public class DsDeviceQryCriteria {
	@ApiModelProperty("设备类型编码")
	private String deviceTypeCode; 		// 设备类型编码
	@ApiModelProperty("防篡改设备名称")
	private String dsdeviceName;		// 防篡改设备名称
	@ApiModelProperty("组织机构（单位名称）")
	private String orgnizationName;		// 组织机构名称
	
	
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
	public String getDsdeviceName() {
		return dsdeviceName;
	}
	public void setDsdeviceName(String dsdeviceName) {
		this.dsdeviceName = dsdeviceName;
	}
	public String getOrgnizationName() {
		return orgnizationName;
	}
	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}
	
	
	
	
	

}
