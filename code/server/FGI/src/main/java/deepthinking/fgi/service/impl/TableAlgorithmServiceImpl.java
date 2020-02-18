package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.service.TableAlgorithmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:53
 */
@Service("tableAlgorithmService")
@Transactional
public class TableAlgorithmServiceImpl extends BaseServiceImpl<TableAlgorithm,Integer> implements TableAlgorithmService {
    @Override
    public PageInfo<TableAlgorithm> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
