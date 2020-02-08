package deepthinking.fgi.model.frontend;

public class ActiveMQDataModel {
	String reportID;   			//报检单编码
	String companyName; 		//检测单位名称
	String staffName;   		//检测员名称
	String longitude;   		//采集地点经度
	String latitude;    		//采集地点维度
	String timeStamp;   		//文件时间戳
	String instrumentName;		//受控仪表名称
	String deviceID;    		//防篡改设备编码
	String url;          		//仪表数据文件地址
	
	public ActiveMQDataModel(){
		this.companyName = "";
		this.deviceID = "";
		this.instrumentName ="";
		this.latitude = "";
		this.longitude = "";
		this.staffName = "";
		this.timeStamp="";
		this.reportID = "";
		this.url = "";	
	}
	
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
