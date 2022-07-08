package model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Map;

/**
 * 分页请求入参
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Data
@ApiModel("分页入参")
public class PageQuery<T>  implements Serializable {
    private static final long serialVersionUID = -8748863600595547741L;

    @ApiModelProperty(value ="当前页，从1开始",required = true)
    @Min(1)
    private Integer pageNo = 1;

    @ApiModelProperty(value ="每页数量",required = true)
    @Min(1)
    private Integer pageSize = 10;

    @ApiModelProperty(value ="排序值 key=xxx value=asc/desc")
    private Map<String, String> sorter;

    @Valid
    @ApiModelProperty(value ="查询条件")
    private T query;
}
