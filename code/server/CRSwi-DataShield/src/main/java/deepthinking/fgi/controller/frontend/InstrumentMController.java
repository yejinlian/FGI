/********************************************
 * 受控仪表设备管理的外部API接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.controller.frontend;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TInstrument;
import deepthinking.fgi.dto.frontend.InstrumentQryCriteria;
import deepthinking.fgi.dto.frontend.TInstrumentDto;
import deepthinking.fgi.service.InstrumentMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="受控仪表设备管理API")
@RestController
@RequestMapping("/instrument")
public class InstrumentMController {
	private static Logger logger = LoggerFactory.getLogger(InstrumentMController.class);

	@Autowired
	private InstrumentMService instrumentMService;

	/**
	 * “添加受控仪表设备”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 添加成功或失败
	 **/
	@PostMapping("/")
	@ApiOperation(notes="添加受控仪表",value="添加受控仪表设备",httpMethod="POST")
	public boolean addInstrument(@RequestBody TInstrument instrument){
		// 需添加统一参数处理
		if(null!=instrument){
			try{
				return instrumentMService.insert(instrument)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	
	/**
	 * “分页查询所有受控仪表设备”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 分页数据
	 **/
	@PostMapping("/pages")
	@ApiOperation(notes="查询所有",value="查询所有受控仪表设备，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<TInstrumentDto> findAllInstruments(int pageNum,int pageSize,@RequestBody InstrumentQryCriteria instrumentQryCriteria){
		try{
			
			return instrumentMService.pageFindModel(pageNum,pageSize,instrumentQryCriteria);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * “对仪表资产编号做唯一性检查”接口
	 * @author zwq
	 * @create 2018-06-24
	 * @return 是否唯一
	 **/
	@GetMapping("/instrumentNo/uniqueness")
	@ApiOperation(notes="仪表资产编号唯一性检查",value="仪表资产编号唯一性检查（返回是否存在）",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "instrumentCode", value = "仪表资产编号", required = true)})
	public boolean checkInstrumentNo(String instrumentCode){
		try{
			return (null!=instrumentMService.selectByPrimaryKey(instrumentCode))?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	/**
	 * “修改受控仪表设备”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 更新成功或失败
	 **/
	@PutMapping("/dsdevice")
	@ApiOperation(notes="修改受控仪表",value="更新受控仪表设备",httpMethod="PUT")
	public boolean updateDSDevice(@RequestBody TInstrument instrument){
		// 需添加统一参数处理
		if(null!=instrument){
			try{
				return instrumentMService.updateByPrimaryKeySelective(instrument)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除受控仪表设备”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/dsdevice")
	@ApiOperation(notes="删除受控仪表",value="删除受控仪表设备",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "instrumentCode", value = "受控仪表资产编号", required = true)})
	public boolean removeDSDevice(@RequestParam String instrumentCode){
		// 需添加统一参数处理
		if(null!=instrumentCode){
			try{
				return instrumentMService.deleteByPrimaryKey(instrumentCode)>0?true:false;
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	

	
}
