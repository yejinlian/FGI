package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableAlgorithmrole;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:47
 */
public interface TableAlgorithmService extends BaseService<TableAlgorithm,Integer>{
    /**
     * 获取公共的和个人的所有算子,仅返回算子基本信息
     * @param username
     * @return
     */
    List<TableAlgorithm> getAllAlgorithm(String username);

    /**
     * 记录算子之间的逻辑关系（当算子有前序算子时，定义前序算子需要满足的逻辑条件，以执行后续算子）
     * @param tableAlgorithmrole
     * @return
     */
    boolean saveAlgorithmLogicRelation(TableAlgorithmrole tableAlgorithmrole);

    /**
     * 添加算子信息
     * @param algth
     * @return
     */
    boolean addAlgorithm(TableAlgorithm algth);

    /**
     * 根据算子ID获取该算子相关的所有信息，包括参数，算法算子关系，算子运行条件
     * @param algthId
     * @return
     */
    TableAlgorithm getAlgorithmById(String algthId);
}
