package com.evans.project.quartz.entity.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.evans.project.database.entiry.dataobject.BaseDO;
import lombok.*;
import com.evans.project.quartz.entity.enums.JobLogStatusEnum;
import com.evans.project.quartz.handler.JobHandler;

import java.time.LocalDateTime;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_job_log")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JobLogDO extends BaseDO {

    /**
     * 日志编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 任务编号
     * 关联 {@link JobDO#getId()}
     */
    private Long jobId;
    /**
     * 处理器的名字
     * 冗余字段 {@link JobDO#getHandlerName()}
     */
    private String handlerName;
    /**
     * 处理器的参数
     * 冗余字段 {@link JobDO#getHandlerParam()}
     */
    private String handlerParam;
    /**
     * 开始执行时间
     */
    private LocalDateTime beginTime;
    /**
     * 结束执行时间
     */
    private LocalDateTime endTime;
    /**
     * 执行时长，单位：毫秒
     */
    private Integer duration;
    /**
     * 状态
     * 枚举 {@link JobLogStatusEnum}
     */
    private Integer status;
    /**
     * 结果数据
     * 成功时，使用 {@link JobHandler#execute(String)} 的结果
     * 失败时，使用 {@link JobHandler#execute(String)} 的异常堆栈
     */
    private String result;

}
