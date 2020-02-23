package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableFunc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/21 15:33
 */
@ApiModel(value = "算子相关信息交互数据模型")
public class AlgorithmModel {
    @ApiModelProperty(value = "算子基本信息")
    private TableAlgorithm tableAlgorithm;
    @ApiModelProperty(value = "算子公式变量信息")
    private List<TableFunc> tableFuncs;

}
