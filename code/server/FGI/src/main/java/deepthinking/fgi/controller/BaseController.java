/********************************************
 * 基础数据的外部API接口
 *
 * @author zwq
 * @create 2018-06-06
 *********************************************/

package deepthinking.fgi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDictionaryValue;
import deepthinking.fgi.service.BaseDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="基础数据API")
@RestController
@RequestMapping("/baseData")
public class BaseController {
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private BaseDataService baseDataService;

	/**
	 * “添加字典值-设备类型”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 添加成功或失败
	 **/
	@PostMapping("/dic/deviceType")
	@ApiOperation(notes="添加字典值",value="添加设备类型",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "add", dataType = "TDictionaryValue", name = "dictionaryValue", value = "设备类型数据结构", required = true)})
	public boolean addDeviceType(@RequestBody TDictionaryValue dictionaryValue){
		// 需添加统一参数处理
		if(null!=dictionaryValue){
			try{
				return baseDataService.insert(dictionaryValue)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	
	/**
	 * “分页查询字典值-设备类型”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 分页数据
	 **/
	@RequestMapping("/dic/deviceType/pages")
	@ApiOperation(notes="查询所有",value="查询所有设备类型，分页返回",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<TDictionaryValue> findAllDeviceTypes(int pageNum,int pageSize){
		try{
			return baseDataService.pageFind(pageNum,pageSize,null);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	/**
	 * “查询所有字典值”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 字典树
	 **/
	@RequestMapping("/dics")
	@ApiOperation(notes="查询所有",value="查询所有字典数据",httpMethod="GET")
	public Map<String,Map<String,String>> findAllDic(){
		try{
			return baseDataService.getAllDics();
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	
	/**
	 * “修改字典值-设备类型”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 更新成功或失败
	 **/
	@PutMapping("/dic/deviceType")
	@ApiOperation(notes="修改单台",value="修改设备类型",httpMethod="PUT")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "update", dataType = "TDictionaryValue", name = "dictionaryValue", value = "设备类型数据结构", required = true)})
	public boolean updateDeviceType(@RequestBody TDictionaryValue dictionaryValue){
		// 需添加统一参数处理
		if(null!=dictionaryValue){
			try{
				return baseDataService.updateByPrimaryKeySelective(dictionaryValue)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除字典值-设备类型”接口
	 * @author zwq
	 * @create 2018-06-06
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/dic/deviceType")
	@ApiOperation(notes="删除单台",value="删除设备类型",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "dicValueCode", value = "设备类型编号", required = true)})
	public boolean removeDeviceType(String dicValueCode){
		// 需添加统一参数处理
		if(null!=dicValueCode){
			try{
				return baseDataService.deleteByPrimaryKey(dicValueCode)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
}
