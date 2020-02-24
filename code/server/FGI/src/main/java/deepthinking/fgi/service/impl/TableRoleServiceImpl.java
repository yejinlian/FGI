package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.TableAlgorithmconditionMapper;
import deepthinking.fgi.dao.mapper.TableAlgorithmroleMapper;
import deepthinking.fgi.dao.mapper.TableRoleMapper;
import deepthinking.fgi.domain.*;
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
    @Resource
    private TableAlgorithmroleMapper tableAlgorithmroleMapper;//算法算子关系mapper
    @Resource
    private TableAlgorithmconditionMapper tableAlgorithmconditionMapper;//算子运行条件mapper

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
        TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
        return roleMapper.selectByExample(tableRoleCriteria);
    }

    @Override
    public AlgorithmRuleSaveDataModel getAlgorithmRuleById(String Id) {
        return null;
    }

    @Override
    public boolean saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel) {
        TableRole tableRole=algorithmRuleSaveDataModel.getTableRole();
        if(tableRole!=null){
            insert(tableRole);
            //获取主键
            int id=getPrimaryKey(tableRole);
            List<AlgorithmRuleDataModel> algorithmRuleDataModels=algorithmRuleSaveDataModel.getAlgorithmRuleDataModelList();
            if(algorithmRuleDataModels.size()>0){
                algorithmRuleDataModels.stream().forEach(data->{
                    data.setRoleId(id);
                    this.saveAlgorithmRuleOne(data);
                });
            }

        }

        return false;
    }

    private int getPrimaryKey(TableRole tableRole){
        return 0;
    }

    @Override
    public boolean saveAlgorithmRuleOne(AlgorithmRuleDataModel algorithmRuleDataModel) {
        return false;
    }

    @Override
    public boolean saveAlgorithmRuleBase(TableRole tableRole) {
        return insert(tableRole)==1;
    }

    @Override
    public boolean modAlgorithmRule(AlgorithmRuleDataModel algorithmRuleDataModel) {
        return false;
    }

    @Override
    public boolean modAlgorithmRuleBase(TableRole tableRole) {
        return updateByPrimaryKeySelective(tableRole)==1;
    }

    @Override
    public boolean delAlgorithmRuleById(String Id) {
        if(Id==null||"".equals(Id)){
            logger.warn("删除规则传入的规则ID不能为空");
            return false;
        }
        try {
            //删除所有算子关系
            TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
            tableAlgorithmroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));
            List<TableAlgorithmrole> tableAlgorithmroles=tableAlgorithmroleMapper.selectByExample(tableAlgorithmroleCriteria);
            if(tableAlgorithmroles.size()>0){
                tableAlgorithmroles.stream().forEach(algorithmrole->{
                    //删除运行条件
                    TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
                    tableAlgorithmconditionCriteria.createCriteria().andAlgorithmroleidEqualTo(algorithmrole.getId());
                    tableAlgorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
                });
            }
            tableAlgorithmroleMapper.deleteByExample(tableAlgorithmroleCriteria);
            //删除规则本身信息
            deleteByPrimaryKey(Integer.parseInt(Id));
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delTableAlgorithmrole(String algorithmroleId) {
        if(algorithmroleId==null||"".equals(algorithmroleId)){
            logger.warn("删除算子关系传入的算法算子关系ID不能为空");
            return false;
        }
        try {
            tableAlgorithmroleMapper.deleteByPrimaryKey(Integer.parseInt(algorithmroleId));
            //删除运行条件
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andAlgorithmroleidEqualTo(Integer.parseInt(algorithmroleId));
            tableAlgorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
