package com.warmstone.micro.base.common;

import com.warmstone.micro.base.util.ObjectUtil;
import lombok.Data;

/**
 * @author warmstone
 * @date 2022-04-06 22:06
 * @description 分页请求入参
 */
@Data
public class PageQuery {

    /**
     * 默认每页数据总数
     */
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认页码
     */
    private static final Integer DEFAULT_CURRENT_PAGE = 1;
    /**
     * 最多一万条，防止刷库
     */
    private static final Integer MAX_PAGE_SIZE = 10000;
    /**
     * 当前页，默认第一页
     */
    private Integer currentPage = DEFAULT_CURRENT_PAGE;
    /**
     * 默认条数
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 总条数
     */
    private Integer totalItem;

    public PageQuery() {}

    /**
     * 构造分页入参，如果页码为null或者小于0，则取默认页码
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     */
    public PageQuery(Integer currentPage, Integer pageSize) {
        this.currentPage = (ObjectUtil.isNull(currentPage) || currentPage <= 0) ? DEFAULT_CURRENT_PAGE : currentPage;
        this.pageSize = getDefaultPageSize(pageSize);
    }

    /**
     * 获取默认的每页条数
     * @param pageSize 条数
     * @return 默认条数
     */
    private Integer getDefaultPageSize(Integer pageSize) {
        return (ObjectUtil.isNull(pageSize) || pageSize <0) ? DEFAULT_PAGE_SIZE : pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
    }

    /**
     * 获取起始行数
     * @return 起始行数
     */
    public Integer getStartRow() {
        return (this.currentPage - 1) * pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = (ObjectUtil.isNull(currentPage) || currentPage <= 0) ? DEFAULT_CURRENT_PAGE : currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = getDefaultPageSize(pageSize);
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }
}
