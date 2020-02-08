/********************************************
 * 备案数据文件信息管理的对外API接口
 *
 * @author zwq
 * @create 2018-07-02
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.dto.frontend.RepDataFileMemDto;
import deepthinking.fgi.dto.frontend.RepDiskSpaceDto;
import deepthinking.fgi.dto.frontend.ReqDataFileMemDto;
import deepthinking.fgi.service.DataFileMService;
import deepthinking.fgi.util.DiskUtils;
import deepthinking.fgi.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value="备案数据文件信息管理API")
@RestController
@RequestMapping("/datafile")
public class DataFileMController {
	private static Logger logger = LoggerFactory.getLogger(DataFileMController.class);

	@Autowired
	private DataFileMService dataFileMService;
	
	
	
	/**
	 * “分页查询备案数据”接口
	 * @author zwq
	 * @create 2018-07-02
	 * @return 分页数据
	 **/
	@GetMapping("/pages")
	@ApiOperation(notes="查询所有数据备案文件",value="查询所有数据备案文件信息，分页返回",httpMethod="GET")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "orderCode", value = "工单编码", required = true)})
	public PageInfo<TDsdataRecord> findDataRecords(int pageNum,int pageSize,int orderCode){
		try{
			HashMap<String,Integer> reqMap = new HashMap<>();
			reqMap.put("orderCode", orderCode);
			return dataFileMService.pageFind(pageNum,pageSize,reqMap);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}


	/**
	 * “获取数据存储空间”接口
	 * @author zwq
	 * @create 2018-07-10
	 * @return 数据存储空间信息
	 **/
	@GetMapping("/diskspaces")
	@ApiOperation(notes="获取系统的存储空间信息",value="查询系统的存储空间信息",httpMethod="GET")
	public RepDiskSpaceDto getDiskSpaces(){
		RepDiskSpaceDto diskSpaceDto = null;
		try{
			diskSpaceDto = new RepDiskSpaceDto();
			String diskSpaceInfo = DiskUtils.getSpace();
			String[]  diskSpaceArray = diskSpaceInfo.split(",");
			if(diskSpaceArray.length==5){
				diskSpaceDto.setTotleSpace(diskSpaceArray[0]);
				diskSpaceDto.setFreeSpace(diskSpaceArray[1]);
				diskSpaceDto.setUsableSpace(diskSpaceArray[2]);
				diskSpaceDto.setFreePercentage(diskSpaceArray[3]);
				diskSpaceDto.setUsablePercentage(diskSpaceArray[4]);
			}

		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return diskSpaceDto;
	}
	
	/**
	 * “分页查询备案数据的空间信息”接口
	 * @author zwq
	 * @create 2018-07-09
	 * @return 分页数据
	 **/
	@PostMapping("/memory/pages")
	@ApiOperation(notes="查询数据备案文件的占用空间",value="查询数据备案文件的占用空间信息，分页返回",httpMethod="POST")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码", required = true)
	,@ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "分页大小", required = true)})
	public PageInfo<RepDataFileMemDto> findDataFileMems(int pageNum,int pageSize, @RequestBody ReqDataFileMemDto dataFileMemDto){
		try{
			return dataFileMService.pageFindDataFileMem(pageNum, pageSize, dataFileMemDto);
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return null;
	}
	
	/**
	 * “删除工单对应的备案数据”接口
	 * @author zwq
	 * @create 2018-07-09
	 * @return 删除成功或失败
	 **/
	@DeleteMapping("/memory")
	@ApiOperation(notes="删除工单对应的备案数据",value="删除工单对应的备案数据",httpMethod="DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "int", name = "orderCode", value = "工单编号", required = true)})
	public boolean removeDataFileMem(int orderCode){
		try{
			HashMap<String,Integer> reqMap = new HashMap<>();
			reqMap.put("orderCode", orderCode);
			List<TDsdataRecord> dsRecords  = dataFileMService.selectAllModelList("selectDataMemRecords", reqMap);
			
			for(TDsdataRecord dsdataRecord:dsRecords){
				// 先删除文件，再删除数据记录
				if(FileUtils.delFile(dsdataRecord.getDataUrl())){
					dataFileMService.deleteByPrimaryKey(dsdataRecord.getDatafileCode());
				}else{
					dataFileMService.deleteByPrimaryKey(dsdataRecord.getDatafileCode());
					logger.error("服务器不存在数据文件："+dsdataRecord.getDatafileName());
				}
			}
			return true;
			
		}catch(Exception ex){
			// 需添加统一异常处理
			logger.error(ex.getMessage());
		}
		return false;
	}
	
	
}
