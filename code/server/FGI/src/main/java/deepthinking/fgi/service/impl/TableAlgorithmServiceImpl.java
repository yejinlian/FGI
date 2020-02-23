package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableAlgorithmrole;
import deepthinking.fgi.service.TableAlgorithmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    @Override
    public List<TableAlgorithm> getAllAlgorithm(String username) {
        return null;
    }

    @Override
    public boolean saveAlgorithmLogicRelation(TableAlgorithmrole tableAlgorithmrole) {
        return false;
    }

    @Override
    public boolean addAlgorithm(TableAlgorithm algth) {
        return false;
    }

    @Override
    public TableAlgorithm getAlgorithmById(String algthId) {
        return null;
    }
}
