/********************************************
 * 文件相关配置类
 *
 * @author zwq
 * @create 2018-06-12
 *********************************************/

package deepthinking.fgi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="path")
public class FilePathConfiguration {
	private static String fwFilePath;			// 固件上传后存放路径
	private static String fwServerPath;        	// 固件文件服务地址
	private static String logServerPath;       	// 设备日志服务地址
	private static String logFilePath;        	// 日志上传后存放路径
	private static String logFilePwd;        	// 日志上传访问密码
	private static String localLogFilePath;		// 日志上传后实际存放路径
	private static String dataServerPath;       // 设备数据服务地址
	private static String dataFilePath;        	// 设备数据上传后存放路径
	private static String dataFilePwd;        	// 设备数据上传访问密码
	private static String localDataFilePath;	// 设备数据上传后实际存放路径
	
	public static String getFwFilePath() {
		return fwFilePath;
	}
	public void setFwFilePath(String _fwFilePath) {
		fwFilePath = _fwFilePath;
	}
	public static String getFwServerPath() {
		return fwServerPath;
	}
	public void setFwServerPath(String _fwServerPath) {
		fwServerPath = _fwServerPath;
	}
	public static String getLogFilePath() {
		return logFilePath;
	}
	public void setLogFilePath(String _logFilePath) {
		logFilePath = _logFilePath;
	}
	public static String getLogFilePwd() {
		return logFilePwd;
	}
	public void setLogFilePwd(String _logFilePwd) {
		logFilePwd = _logFilePwd;
	}
	public static String getDataFilePath() {
		return dataFilePath;
	}
	public void setDataFilePath(String _dataFilePath) {
		dataFilePath = _dataFilePath;
	}
	public static String getDataFilePwd() {
		return dataFilePwd;
	}
	public void setDataFilePwd(String _dataFilePwd) {
		dataFilePwd = _dataFilePwd;
	}
	public static String getLocalLogFilePath() {
		return localLogFilePath;
	}
	public static void setLocalLogFilePath(String _localLogFilePath) {
		localLogFilePath = _localLogFilePath;
	}
	public static String getLocalDataFilePath() {
		return localDataFilePath;
	}
	public static void setLocalDataFilePath(String _localDataFilePath) {
		localDataFilePath = _localDataFilePath;
	}
	public static String getDataServerPath() {
		return dataServerPath;
	}
	public static void setDataServerPath(String dataServerPath) {
		FilePathConfiguration.dataServerPath = dataServerPath;
	}
	public static String getLogServerPath() {
		return logServerPath;
	}
	public static void setLogServerPath(String logServerPath) {
		FilePathConfiguration.logServerPath = logServerPath;
	}
	
	
}
