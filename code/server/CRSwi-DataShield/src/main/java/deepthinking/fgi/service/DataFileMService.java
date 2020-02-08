/********************************************
 * 备案数据文件信息管理的Service层接口
 *
 * @author zwq
 * @create 2018-07-02
 *********************************************/

package deepthinking.fgi.service;

import java.lang.reflect.InvocationTargetException;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.dto.frontend.RepDataFileMemDto;
import deepthinking.fgi.dto.frontend.ReqDataFileMemDto;

public interface DataFileMService extends BaseService<TDsdataRecord,Integer>{
	PageInfo<RepDataFileMemDto> pageFindDataFileMem(int pageNum, int pageSize, ReqDataFileMemDto dataFileMemDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
