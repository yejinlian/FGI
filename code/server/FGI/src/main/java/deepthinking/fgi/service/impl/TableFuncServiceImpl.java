package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableFunc;
import deepthinking.fgi.service.TableFuncService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:53
 */
@Service("tableFuncService")
@Transactional
public class TableFuncServiceImpl extends BaseServiceImpl<TableFunc,Integer> implements TableFuncService {
    @Override
    public PageInfo<TableFunc> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
