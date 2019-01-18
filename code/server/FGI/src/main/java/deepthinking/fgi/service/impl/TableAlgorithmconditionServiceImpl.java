package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableAlgorithmcondition;
import deepthinking.fgi.service.TableAlgorithmconditionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:49
 */
@Service("tableAlgorithmconditionService")
@Transactional
public class TableAlgorithmconditionServiceImpl extends BaseServiceImpl<TableAlgorithmcondition,Integer> implements TableAlgorithmconditionService{
    @Override
    public PageInfo<TableAlgorithmcondition> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
