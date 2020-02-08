/********************************************
 * 工单管理的对外API接口
 *
 * @author zwq
 * @create 2018-06-10
 *********************************************/

package deepthinking.fgi.controller.frontend;

import java.util.HashMap;
import java.util.List;

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

import deepthinking.fgi.domain.TDatashieldDevice;
import deepthinking.fgi.domain.TInstrument;
import deepthinking.fgi.domain.TWorkorders;
import deepthinking.fgi.dto.frontend.OrderMutexDto;
import deepthinking.fgi.dto.frontend.WorkOrderQryCriteria;
import deepthinking.fgi.dto.frontend.WorkorderDto;
import deepthinking.fgi.service.DSDeviceMService;
import deepthinking.fgi.service.InstrumentMService;
import deepthinking.fgi.service.WorkOrdersMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="工单管理API")
@RestController
@RequestMapping("/workOrders")
public class WorkOrdersMController {
	private static Logger logger = LoggerFactory.getLogger(WorkOrdersMController.class);

	@Autowired
	private WorkOrdersMService workOrdersMService;
	@Autowired
	private InstrumentMService instrumentMService;
	@Autowired
	private DSDeviceMService dSDeviceMService;
	
	
	/**
	 * @author zwq
	 * @create 2018-06-10
	 * @param workorders 工单数据结构
	 * @return 添加成功或失败
	 **/
	@PostMapping("/")
	@ApiOperation(notes="添加工单",value="添加工单",httpMethod="POST")
	public boolean addWorkOrder(@RequestBody TWorkorders workorders){
		// 需添加统一参数处理
		if(null!=workorders){
			try{
				workorders.setOrderStatus("T-NEW-01");
				// 0代表不一致，1代表一致，2代表N/A
				workorders.setDataConsistency(2);
				TInstrument instrument = instrumentMService.selectByPrimaryKey(workorders.getInstrumentCode());
				
				if(null!=instrument && !instrument.getDeviceStatusCode().equals("DS-OUT-01")){
					TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(workorders.getDsdeviceCode());
					if(null!=datashieldDevice && !datashieldDevice.getDeviceStatusCode().equals("DS-OUT-01")){
						return workOrdersMService.insert(workorders)>0?true:false;
					}else{
						return false;
					}
					
				}else{
					return false;
				}
				
				
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * “分页查询工单”接口
	 * @author zwq
	 * @create 2018-06-02
	 * @return 分页数据
	 **/
	@PostMapping("/pages")
	@ApiOperation(notes="查询所有",value="查询所有工单，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<WorkorderDto> findAllWorkOrders(int pageNum,int pageSize,@RequestBody WorkOrderQryCriteria workOrderQryCriteria){
		try{
			// 如果是设备绑定查询，隐藏的查询条件：【工单状态】== “签发”，即是workOrderQryCriteria.orderStatus="T-SIGHED-02"
			// 如果是设备解绑查询，隐藏的查询条件：【工单状态】== “绑定出库”，即是workOrderQryCriteria.orderStatus="T-OUT-03"
			// 巡检完成后，防篡改设备中的数据上传后台结束，此时将工单状态=>【数据备案】，即是workOrderQryCriteria.orderStatus="T-DAT-04"
			return workOrdersMService.pageFindModel(pageNum,pageSize,workOrderQryCriteria);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	
	
	/**
	 * “报检单号唯一性检测”接口
	 * @author zwq
	 * @create 2018-06-24
	 * @return 是否唯一
	 **/
	@GetMapping("/orderNo/uniqueness")
	@ApiOperation(notes="报检单号唯一性约束",value="报检单号唯一性约束",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderNo", value = "报检单号", required = true)})
	public boolean isOrderNoUnique(String orderNo){
		try{
			HashMap<String,Object> queryCriteria = new HashMap<String,Object>();
        	queryCriteria.put("inspection_order_no", orderNo);
        	List<TWorkorders> workorders = workOrdersMService.selectByNonPrimaryKey(queryCriteria);
			return (workorders.size()>0)?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “互斥检测”接口
	 * @author zwq
	 * @create 2018-06-24
	 * @return 是否满足互斥限制
	 **/
	@PostMapping("/mutex/dsdevice")
	@ApiOperation(notes="互斥限制",value="互斥：同一【单位+巡检员+出工日期】组合条件下，只能对应一台防篡改设备",httpMethod="POST")
	public boolean isMutex(@RequestBody OrderMutexDto orderMutex){
		try{
			HashMap<String,Object> queryCriteria = new HashMap<String,Object>();
        	queryCriteria.put("organization_code", orderMutex.getOrgnizationCode());
        	queryCriteria.put("inspector_code", orderMutex.getInspectorCode());
        	queryCriteria.put("work_date", orderMutex.getWorkDate());
        	queryCriteria.put("dsdevice_code", orderMutex.getDsdeviceCode());
			List<TWorkorders> workorders = workOrdersMService.selectByNonPrimaryKey(queryCriteria);
			return (workorders.size()>0)?true:false;
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
	/**
	 * “修改工单”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 更新成功或失败
	 **/
	@PutMapping("/")
	@ApiOperation(notes="更新工单",value="更新工单",httpMethod="PUT")
	public boolean updateWorkOrder(@RequestBody TWorkorders tWorkorders){
		// 需添加统一参数处理
		if(null!=tWorkorders){
			try{
				TInstrument instrument = instrumentMService.selectByPrimaryKey(tWorkorders.getInstrumentCode());
				
				if(null!=instrument && !instrument.getDeviceStatusCode().equals("DS-OUT-01")){
					TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(tWorkorders.getDsdeviceCode());
					if(null!=datashieldDevice && !datashieldDevice.getDeviceStatusCode().equals("DS-OUT-01")){
						return workOrdersMService.updateByPrimaryKeySelective(tWorkorders)>0?true:false;
					}else{
						return false;
					}
					
				}else{
					return false;
				}
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * “删除工单”接口
	 * @author zwq
	 * @create 2018-06-10
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/")
	@ApiOperation(notes="删除工单",value="删除工单",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "orderCode", value = "工单编号", required = true)})
	public boolean removeWorkOrder(Integer orderCode){
		// 需添加统一参数处理
		if(0!=orderCode){
			try{
				TWorkorders workorders = workOrdersMService.selectByPrimaryKey(orderCode);
				if(null!=workorders){
					TInstrument instrument = instrumentMService.selectByPrimaryKey(workorders.getInstrumentCode());
					
					if(null!=instrument && !instrument.getDeviceStatusCode().equals("DS-OUT-01")){
						TDatashieldDevice datashieldDevice = dSDeviceMService.selectByPrimaryKey(workorders.getDsdeviceCode());
						if(null!=datashieldDevice && !datashieldDevice.getDeviceStatusCode().equals("DS-OUT-01")){
							if(workOrdersMService.deleteByPrimaryKey(orderCode)>0){
								// 如果设备状态是【其他】，删除记录，【设备状态】->DS-IN-01
								try{
								
									TInstrument _instrument = new TInstrument();
									_instrument.setInstrumentCode(instrument.getInstrumentCode());
									_instrument.setDeviceStatusCode("DS-IN-01");
				                	instrumentMService.updateByPrimaryKeySelective(_instrument);
								}catch (Exception ex){
									// 需添加统一异常处理
									logger.error(ex.getMessage());
								}
								try{
				                	TDatashieldDevice _datashieldDevice = new TDatashieldDevice();
				                	_datashieldDevice.setDsdeviceCode(datashieldDevice.getDsdeviceCode());
				                	_datashieldDevice.setDeviceStatusCode("DS-IN-01");
					            	dSDeviceMService.updateByPrimaryKeySelective(_datashieldDevice);
								}catch(Exception ex){
									// 需添加统一异常处理
									logger.error(ex.getMessage());
								}
							}
						}else{
							return false;
						}
						
					}else{
						return false;
					}
				}else{
					return false;
				}
				
			}catch (Exception ex){
				// 需添加统一异常处理
				logger.error(ex.getMessage());
			}
		}
		return true;
	}
}
