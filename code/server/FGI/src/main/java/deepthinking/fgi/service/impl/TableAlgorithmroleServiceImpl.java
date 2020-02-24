package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableAlgorithmrole;
import deepthinking.fgi.service.TableAlgorithmroleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:51
 */
@Service("tableAlgorithmroleService")
@Transactional
public class TableAlgorithmroleServiceImpl extends BaseServiceImpl<TableAlgorithmrole,Integer> implements TableAlgorithmroleService {
    @Override
    public PageInfo<TableAlgorithmrole> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
