package model;

import java.util.List;

/**
 * 通用mapper
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
public interface BaseMapper<BO> {

    /**
     * 单条新增
     * @param bo
     * @return
     */
    int insert(BO bo);

    /**
     * 单条删除
     * @param bo
     * @return
     */
    int delete(BO bo);

    /**
     * 批量删除
     * @param bo
     * @return
     */
    int deletes(BO bo);

    /**
     * 单条更新
     * @param bo
     * @return
     */
    int update(BO bo);

    /**
     * 批量更新
     * @param BOs
     * @return
     */
    int updates(List<BO> BOs);

    /**
     * 列表查询
     * @param bo
     * @return
     */
    List<BO> list(BO bo);

    /**
     * 单条查询
     * @param bo
     * @return
     */
    BO select(BO bo);

    /**
     * 批量新增
     * @param BOs
     * @return
     */
    int inserts(List<BO> BOs);
}
