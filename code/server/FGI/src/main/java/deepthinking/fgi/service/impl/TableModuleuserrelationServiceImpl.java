package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableModuleuserrelation;
import deepthinking.fgi.service.TableModuleuserrelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:56
 */
@Service("tableModuleuserrelationService")
@Transactional
public class TableModuleuserrelationServiceImpl extends BaseServiceImpl<TableModuleuserrelation,Integer> implements TableModuleuserrelationService {
    @Override
    public PageInfo<TableModuleuserrelation> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
