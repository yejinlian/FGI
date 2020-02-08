/********************************************
 * 组织机构管理的外部API接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.controller.frontend;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TOrgnization;
import deepthinking.fgi.dto.frontend.OrgnizationQryCriteria;
import deepthinking.fgi.service.OrgnizationMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="组织机构管理API")
@RestController
@RequestMapping("/orgnization")
public class OrgnizationMController {
	private static Logger logger = LoggerFactory.getLogger(OrgnizationMController.class);
	
	@Autowired
	private OrgnizationMService orgnizationMService;

	/**
	 * “添加新单位”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 添加成功或失败
	 **/
	@PostMapping("/")
	@ApiOperation(notes="新增单位",value="组织机构新增",httpMethod="POST")
	public boolean addOrgnization(@RequestBody TOrgnization orgnization){
		// 需添加统一参数处理
		if(null!=orgnization){
			try{
				return orgnizationMService.insert(orgnization)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	
	/**
	 * “分页查询所有组织机构”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 分页数据
	 **/
	@PostMapping("/pages")
	@ApiOperation(notes="查询所有",value="查询所有单位，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<TOrgnization> findAllOrgnizations(int pageNum,int pageSize,@RequestBody OrgnizationQryCriteria dsOrgnizationQryCriteria){
		try{
			
			return orgnizationMService.pageFind(pageNum,pageSize,dsOrgnizationQryCriteria);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	
	/**
	 * “对单位名称做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-13
	 * @return 是否唯一
	 **/
	@GetMapping("/check/name")
	@ApiOperation(notes="查询单位名称",value="查询单位名称（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "orgnization_name", value = "单位名称", required = true)})
	public boolean checkName(String orgnization_name){
		try{
			HashMap<String,String> parameter = new HashMap<String,String>();
			parameter.put("orgnizationName", orgnization_name);
			return orgnizationMService.selectAllModel(parameter).size()>0?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “对单位名称做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-13
	 * @return 是否唯一
	 **/
	@GetMapping("/check/name/pk")
	@ApiOperation(notes="查询单位名称",value="查询单位名称（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "orgnizationName", value = "单位名称", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "orgnizationCode", value = "单位编码（PK）", required = true)})
	public boolean checkName(String orgnizationName,int orgnizationCode){
		try{
			HashMap<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("orgnizationName", orgnizationName);
			parameter.put("orgnizationCode", orgnizationCode);
			return orgnizationMService.selectAllModel(parameter).size()>0?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “修改组织机构”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 更新成功或失败
	 **/
	@PutMapping("/")
	@ApiOperation(notes="更新单位",value="更新单位",httpMethod="PUT")
	public boolean updateOrgnization(@RequestBody TOrgnization orgnization){
		// 需添加统一参数处理
		if(null!=orgnization){
			try{
				return orgnizationMService.updateByPrimaryKeySelective(orgnization)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除组织结构”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/")
	@ApiOperation(notes="删除单位",value="删除单位",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "orgnizationCode", value = "单位编号", required = true)})
	public boolean removeOrgnization(Integer orgnizationCode){
		// 需添加统一参数处理
		if(0!=orgnizationCode){
			try{
				return orgnizationMService.deleteByPrimaryKey(orgnizationCode)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
}
