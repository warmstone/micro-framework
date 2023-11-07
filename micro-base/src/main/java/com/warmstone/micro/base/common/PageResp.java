package com.warmstone.micro.base.common;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author warmstone
 * @date 2023/8/15 20:45
 * @description
 */
@Data
public class PageResp<T> {

    private Integer currentPage;

    private Integer pageSize;

    private Long total;

    private Integer totalPage;

    private List<T> list;

    public static <T> PageResp<T> restPage(Page<T> page) {
        PageResp<T> pageResp = new PageResp<>();
        pageResp.setCurrentPage(page.getPageNum());
        pageResp.setPageSize(page.getPageSize());
        pageResp.setTotal(page.getTotal());
        pageResp.setList(page.getResult());
        pageResp.setTotalPage(page.getPages());
        return pageResp;
    }
}
