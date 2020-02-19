package deepthinking.fgi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/19 12:05
 */
@ApiModel(value = "模板包含字段")
public class ModuleField {
    @ApiModelProperty(value = "ID")
    private Integer id;
//    @ApiModelProperty(value = "模板ID")
//    private Integer moduleid;
    @ApiModelProperty(value = "字段名称")
    private String fieldname;
    @ApiModelProperty(value = "字段类型")
    private String fieldtype;
    @ApiModelProperty(value = "备注")
    private String remark;

    public ModuleField() {
    }

    public ModuleField(Integer id, String fieldname, String fieldtype, String remark) {
        this.id = id;
        this.fieldname = fieldname;
        this.fieldtype = fieldtype;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
