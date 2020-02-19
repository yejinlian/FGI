package deepthinking.fgi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/19 12:00
 */
@ApiModel(value = "模板交互数据模型")
public class Module {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "模板名称")
    private String modulename;
    @ApiModelProperty(value = "数据库连接")
    private String sqlurl;
    @ApiModelProperty(value = "对应物理表")
    private String tab;
    @ApiModelProperty(value = "模板组")
    private String modulegroup;
    @ApiModelProperty(value = "模板描述")
    private String des;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "模板所含字段")
    private List<ModuleField> moduleFields;

    public Module() {
    }

    public Module(Integer id, String modulename, String sqlurl, String tab, String modulegroup, String des, String remark, List<ModuleField> moduleFields) {
        this.id = id;
        this.modulename = modulename;
        this.sqlurl = sqlurl;
        this.tab = tab;
        this.modulegroup = modulegroup;
        this.des = des;
        this.remark = remark;
        this.moduleFields = moduleFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getSqlurl() {
        return sqlurl;
    }

    public void setSqlurl(String sqlurl) {
        this.sqlurl = sqlurl;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getModulegroup() {
        return modulegroup;
    }

    public void setModulegroup(String modulegroup) {
        this.modulegroup = modulegroup;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ModuleField> getModuleFields() {
        return moduleFields;
    }

    public void setModuleFields(List<ModuleField> moduleFields) {
        this.moduleFields = moduleFields;
    }
}
