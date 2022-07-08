package model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据出参
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Data
@ToString
@EqualsAndHashCode
@ApiModel("分页数据出参")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 108717927673839961L;

    @ApiModelProperty(value ="当前页，起始值1")
    private Integer pageNo;

    @ApiModelProperty(value ="每页数量")
    private Integer pageSize;

    @ApiModelProperty(value ="总条目数")
    private Long total;

    @ApiModelProperty(value ="总页数")
    private Integer maxPage;

    @ApiModelProperty(value ="当前查询返回列表")
    private List<T> items;
}
