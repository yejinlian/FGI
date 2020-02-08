/********************************************
 * 人员权限管理的对外API接口
 *
 * @author zwq
 * @create 2018-06-02
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

import deepthinking.fgi.domain.TStaff;
import deepthinking.fgi.dto.frontend.StaffQryCriteria;
import deepthinking.fgi.dto.frontend.TStaffDto;
import deepthinking.fgi.dto.frontend.VerfyLoginDto;
import deepthinking.fgi.service.StaffMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="人员权限管理API")
@RestController
@RequestMapping("/staff")
public class StaffMController {
	private static Logger logger = LoggerFactory.getLogger(StaffMController.class);

	@Autowired
	private StaffMService staffMService;

	/**
	 * “添加人员”接口
	 * @author zwq
	 * @create 2018-06-02
	 * @param staff 员工数据结构
	 * @return 添加成功或失败
	 **/
	@PostMapping("/")
	@ApiOperation(notes="添加员工",value="添加用户",httpMethod="POST")
	public boolean addStaff(@RequestBody TStaff staff){
		// 需添加统一参数处理
		if(null!=staff){
			try{
				return staffMService.insert(staff)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * “分页查询人员权限”接口
	 * @author zwq
	 * @create 2018-06-02
	 * @return 分页数据
	 **/
	@PostMapping("/pages")
	@ApiOperation(notes="查询所有",value="查询所有员工，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<TStaffDto> findAllStaffs(int pageNum,int pageSize,@RequestBody StaffQryCriteria staffQryCriteria){
		try{
			return staffMService.pageFindModel(pageNum,pageSize,staffQryCriteria);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	
	/**
	 * “对员工工号做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-13
	 * @return 是否唯一
	 **/
	@GetMapping("/check/number")
	@ApiOperation(notes="查询员工工号",value="查询工号（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "staffNumber", value = "员工工号", required = true)})
	public boolean checkName(String staffNumber){
		try{
			HashMap<String,String> parameter = new HashMap<String,String>();
			parameter.put("staffNumber", staffNumber);
			return staffMService.selectAllModel(parameter).size()>0?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “对员工工号做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-13
	 * @return 是否唯一
	 **/
	@GetMapping("/check/number/pk")
	@ApiOperation(notes="查询员工工号",value="查询工号（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "staffNumber", value = "员工工号", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "staffCode", value = "员工编号（主键）", required = true)})
	public boolean checkName(String staffNumber,Integer staffCode){
		try{
			HashMap<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("staffNumber", staffNumber);
			parameter.put("staffCode", staffCode);
			return staffMService.selectAllModel(parameter).size()>0?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “修改人员权限”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 更新成功或失败
	 **/
	@PutMapping("/")
	@ApiOperation(notes="更新人员",value="更新人员权限",httpMethod="PUT")
	public boolean updateStaff(@RequestBody TStaff staff){
		// 需添加统一参数处理
		if(null!=staff){
			try{
				return staffMService.updateByPrimaryKeySelective(staff)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除人员权限”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/")
	@ApiOperation(notes="删除人员",value="删除人员权限",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "staffCode", value = "人员编号", required = true)})
	public boolean removeStaff(Integer staffCode){
		// 需添加统一参数处理
		if(0!=staffCode){
			try{
				return staffMService.deleteByPrimaryKey(staffCode)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “登录验证”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 登录成功返回完整数据结构，失败返回null
	 **/
	@PostMapping("/verfyLogin")
	@ApiOperation(notes="登录验证",value="验证人员权限",httpMethod="POST")
	public TStaffDto verfyLogin(@RequestBody VerfyLoginDto verfyLoginDto){
		// 需添加统一参数处理
		if(null!=verfyLoginDto){
			try{
				return staffMService.verfyLogin(verfyLoginDto);
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return null;
	}
}
