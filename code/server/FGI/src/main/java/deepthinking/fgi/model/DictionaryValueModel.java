/********************************************
 * 数据字典值模型
 *
 * @author zwq
 * @create 2018-06-07 0:33
 *********************************************/

package deepthinking.fgi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("字典值DTO")
public class DictionaryValueModel {
	@ApiModelProperty("字典值名称")
	private String dicValueName;	   // 字典值名称
	@ApiModelProperty("字典值说明")
	private String dicValueComment;	   // 字典值说明
	
	public String getDicValueName() {
		return dicValueName;
	}
	public void setDicValueName(String dicValueName) {
		this.dicValueName = dicValueName;
	}
	public String getDicValueComment() {
		return dicValueComment;
	}
	public void setDicValueComment(String dicValueComment) {
		this.dicValueComment = dicValueComment;
	}
	
	
}
