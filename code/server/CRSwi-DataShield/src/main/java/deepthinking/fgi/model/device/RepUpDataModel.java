/********************************************
 * 服务端回复设备数据上载结构
 *
 * @author zwq
 * @create 2018-06-03 22:38
 *********************************************/

package deepthinking.fgi.model.device;


public class RepUpDataModel {
    private String url;        		// 上传位置,含用户名
    private String serialID;      	// sync密码，※迷惑他人，所以用serialID
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}


}
