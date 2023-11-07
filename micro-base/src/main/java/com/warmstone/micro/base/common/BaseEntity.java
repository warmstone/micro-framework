package com.warmstone.micro.base.common;

import java.time.LocalDateTime;

/**
 * @author warmstone
 * @date 2023/8/15 20:51
 * @description
 */
public class BaseEntity {

    private Long creator;

    private Long updater;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
