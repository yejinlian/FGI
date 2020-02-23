package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableRole;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:47
 */
public interface TableRoleService extends BaseService<TableRole,Integer> {

    /**
     * 批量插入
     * @param filePath 文件地址
     * @Author 王若山
     * @return
     */
    boolean batchInsert(String filePath);
}
