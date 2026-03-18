package com.evans.project.quartz.entity.vo;

import com.evans.project.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.evans.project.quartz.entity.enums.JobStatusEnum;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobPageReqVO extends PageParam {

    private String name;

    /**
     * {@link JobStatusEnum}
     */
    private Integer status;

    private String handlerName;

}
