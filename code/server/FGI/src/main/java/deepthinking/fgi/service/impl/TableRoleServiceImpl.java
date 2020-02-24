package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.TableRoleMapper;
import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.AlgorithmRuleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUtils;
import deepthinking.fgi.util.JsonListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:58
 */
@Service("tableRoleService")
public class TableRoleServiceImpl extends BaseServiceImpl<TableRole,Integer> implements TableRoleService {

    private static Logger logger = LoggerFactory.getLogger(TableRoleServiceImpl.class);

    @Resource
    private TableRoleMapper roleMapper;

    @Override
    public PageInfo<TableRole> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    /**
     * 批量插入
     * @param filePath 文件地址
     * @Author 王若山
     * @return
     */
    @Override
    public boolean batchInsert(String filePath) {
        try{
            roleMapper.batchInsert(JsonListUtil.jsonToList(FileUtils.readTxtFile(filePath), TableRole.class));
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public List<TableRole> GetAllAlgorithmRule(String username) {
        return null;
    }

    @Override
    public AlgorithmRuleSaveDataModel getAlgorithmRuleById(String Id) {
        return null;
    }

    @Override
    public boolean saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel) {
        return false;
    }

    @Override
    public boolean saveAlgorithmRuleOne(AlgorithmRuleDataModel algorithmRuleDataModel) {
        return false;
    }

    @Override
    public boolean saveAlgorithmRuleBase(TableRole tableRole) {
        return false;
    }

    @Override
    public boolean modAlgorithmRule(AlgorithmRuleDataModel algorithmRuleDataModel) {
        return false;
    }

    @Override
    public boolean modAlgorithmRuleBase(TableRole tableRole) {
        return false;
    }

    @Override
    public boolean delAlgorithmRuleById(String Id) {
        return false;
    }

    @Override
    public boolean delTableAlgorithmrole(String algorithmroleId) {
        return false;
    }
}
