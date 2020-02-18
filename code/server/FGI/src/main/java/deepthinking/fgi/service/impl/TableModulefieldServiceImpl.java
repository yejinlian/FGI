package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.service.TableModulefieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:55
 */
@Service("tableModulefieldService")
@Transactional
public class TableModulefieldServiceImpl extends BaseServiceImpl<TableModulefield,Integer> implements TableModulefieldService {
    @Override
    public PageInfo<TableModulefield> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
