package com.evans.project.quartz.entity.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.evans.project.database.entiry.dataobject.BaseDO;
import lombok.*;
import com.evans.project.quartz.entity.enums.JobStatusEnum;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_job")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JobDO extends BaseDO {

    /**
     * 任务编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务状态
     * 枚举 {@link JobStatusEnum}
     */
    private Integer status;
    /**
     * 处理器的名字
     */
    private String handlerName;
    /**
     * 处理器的参数
     */
    private String handlerParam;
    /**
     * CRON 表达式
     */
    private String cronExpression;

}
