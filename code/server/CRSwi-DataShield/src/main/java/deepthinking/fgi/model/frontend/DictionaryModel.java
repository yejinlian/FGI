/********************************************
 * 数据字典行列模型
 *
 * @author zwq
 * @create 2018-06-07 0:33
 *********************************************/

package deepthinking.fgi.model.frontend;

public class DictionaryModel {

	private Integer dicCode;	   		// 字典编码
	private String dicName;		   	   	// 字典名称
	private String dicValueCode;	   	// 字典值编码
	private String dicValueName;	   	// 字典值名称
	
	public Integer getDicCode() {
		return dicCode;
	}
	public void setDicCode(Integer dicCode) {
		this.dicCode = dicCode;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicValueCode() {
		return dicValueCode;
	}
	public void setDicValueCode(String dicValueCode) {
		this.dicValueCode = dicValueCode;
	}
	public String getDicValueName() {
		return dicValueName;
	}
	public void setDicValueName(String dicValueName) {
		this.dicValueName = dicValueName;
	}
	
	
}
