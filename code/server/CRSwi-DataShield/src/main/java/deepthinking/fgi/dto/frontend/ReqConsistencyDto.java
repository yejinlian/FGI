/********************************************
 * 前端请求数据一致性的数据结构
 *
 * @author zwq
 * @create 2018-06-03 19:57
 *********************************************/

package deepthinking.fgi.dto.frontend;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求数据一致性验证的数据结构")
public class ReqConsistencyDto {
	@ApiModelProperty("工单编码")
	private String orderCode;			// 工单编码
	@ApiModelProperty("报检单号")
	private String inspectionOrderNo;	// 报检单号
	@ApiModelProperty("数据文件编码")
	private int datafileCode;		// 数据文件编码
	@ApiModelProperty("防篡改仪表编码")
    private String dsdeviceCode;
	@ApiModelProperty("巡检员编码")
	private String inspector_code;      // 巡检员编码
	@ApiModelProperty("文件MD5和名称的对应关系")
	Map<String,String> md5_fileName;

	
	public String getInspectionOrderNo() {
		return inspectionOrderNo;
	}
	public void setInspectionOrderNo(String inspectionOrderNo) {
		this.inspectionOrderNo = inspectionOrderNo;
	}
	public String getInspector_code() {
		return inspector_code;
	}
	public void setInspector_code(String inspector_code) {
		this.inspector_code = inspector_code;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Map<String, String> getMd5_fileName() {
		return md5_fileName;
	}
	public void setMd5_fileName(Map<String, String> md5_fileName) {
		this.md5_fileName = md5_fileName;
	}
	public String getDsdeviceCode() {
		return dsdeviceCode;
	}
	public void setDsdeviceCode(String dsdeviceCode) {
		this.dsdeviceCode = dsdeviceCode;
	}
	public int getDatafileCode() {
		return datafileCode;
	}
	public void setDatafileCode(int datafileCode) {
		this.datafileCode = datafileCode;
	}

	
	
	

	
	
	
}
