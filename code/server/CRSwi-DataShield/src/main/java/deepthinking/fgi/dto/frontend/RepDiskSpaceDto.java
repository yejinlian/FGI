/********************************************
 * 磁盘空间信息DTO
 *
 * @author zwq
 * @create 2018-07-10
 *********************************************/
package deepthinking.fgi.dto.frontend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务器回复的磁盘空间信息")
public class RepDiskSpaceDto {
	@ApiModelProperty("总存储空间")
	String totleSpace;
	@ApiModelProperty("剩余存储空间")
	String freeSpace;
	@ApiModelProperty("已使用存储空间")
	String usableSpace;
	@ApiModelProperty("剩余空间百分比")
	String freePercentage;
	@ApiModelProperty("已使用空间百分比")
	String usablePercentage;
	
	public String getTotleSpace() {
		return totleSpace;
	}
	public void setTotleSpace(String totleSpace) {
		this.totleSpace = totleSpace;
	}
	public String getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}
	public String getUsableSpace() {
		return usableSpace;
	}
	public void setUsableSpace(String usableSpace) {
		this.usableSpace = usableSpace;
	}
	public String getFreePercentage() {
		return freePercentage;
	}
	public void setFreePercentage(String freePercentage) {
		this.freePercentage = freePercentage;
	}
	public String getUsablePercentage() {
		return usablePercentage;
	}
	public void setUsablePercentage(String usablePercentage) {
		this.usablePercentage = usablePercentage;
	}
	
	
}
