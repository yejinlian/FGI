package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 算法算子关系
 */
@ApiModel(value = "算法算子关系")
public class TableAlgorithmrole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.RoleID
     * 规则ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则ID")
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.AlgorithmID
     * 算子ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "算子ID")
    private Integer algorithmid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.PreAlgorithmID
     * 前序算子ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "前序算子ID")
    private Integer prealgorithmid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.Des
     * 描述
     * @mbg.generated
     */
    @ApiModelProperty(value = "描述")
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmrole.Remark
     * 备注
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 算子模块
     */
    @ApiModelProperty(hidden = true)
    private TableAlgorithm tableAlgorithm;

    /**
     * 算子运算条件
     */
    @ApiModelProperty(hidden = true)
    private List<TableAlgorithmcondition> tableAlgorithmconditionList;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.ID
     *
     * @return the value of table_algorithmrole.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.ID
     *
     * @param id the value for table_algorithmrole.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.RoleID
     *
     * @return the value of table_algorithmrole.RoleID
     *
     * @mbg.generated
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.RoleID
     *
     * @param roleid the value for table_algorithmrole.RoleID
     *
     * @mbg.generated
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.AlgorithmID
     *
     * @return the value of table_algorithmrole.AlgorithmID
     *
     * @mbg.generated
     */
    public Integer getAlgorithmid() {
        return algorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.AlgorithmID
     *
     * @param algorithmid the value for table_algorithmrole.AlgorithmID
     *
     * @mbg.generated
     */
    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.PreAlgorithmID
     *
     * @return the value of table_algorithmrole.PreAlgorithmID
     *
     * @mbg.generated
     */
    public Integer getPrealgorithmid() {
        return prealgorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.PreAlgorithmID
     *
     * @param prealgorithmid the value for table_algorithmrole.PreAlgorithmID
     *
     * @mbg.generated
     */
    public void setPrealgorithmid(Integer prealgorithmid) {
        this.prealgorithmid = prealgorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.Des
     *
     * @return the value of table_algorithmrole.Des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.Des
     *
     * @param des the value for table_algorithmrole.Des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmrole.Remark
     *
     * @return the value of table_algorithmrole.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmrole.Remark
     *
     * @param remark the value for table_algorithmrole.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public TableAlgorithm getTableAlgorithm() {
        return tableAlgorithm;
    }

    public void setTableAlgorithm(TableAlgorithm tableAlgorithm) {
        this.tableAlgorithm = tableAlgorithm;
    }

    public List<TableAlgorithmcondition> getTableAlgorithmconditionList() {
        return tableAlgorithmconditionList;
    }

    public void setTableAlgorithmconditionList(List<TableAlgorithmcondition> tableAlgorithmconditionList) {
        this.tableAlgorithmconditionList = tableAlgorithmconditionList;
    }
}