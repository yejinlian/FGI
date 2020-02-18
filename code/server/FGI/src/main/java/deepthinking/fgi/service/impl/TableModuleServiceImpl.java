package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.service.TableModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:56
 */
@Service("tableModuleService")
@Transactional
public class TableModuleServiceImpl extends BaseServiceImpl<TableModule,Integer> implements TableModuleService {
    @Override
    public PageInfo<TableModule> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
