package com.evans.project.quartz.handler;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.evans.project.quartz.entity.enums.JobDataKeyEnum;
import com.evans.project.quartz.service.JobLogFrameworkService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static cn.hutool.core.exceptions.ExceptionUtil.getRootCauseMessage;

/**
 * @author Evans
 * @date 2026/3/17
 * 基础 Job 调用者，负责调用 {@link JobHandler#execute(String)} 执行任务
 */
@Slf4j
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class JobHandlerInvoker extends QuartzJobBean {

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private JobLogFrameworkService jobLogFrameworkService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获得 Job 数据
        Long jobId = context.getMergedJobDataMap().getLong(JobDataKeyEnum.JOB_ID.name());
        String jobHandlerName = context.getMergedJobDataMap().getString(JobDataKeyEnum.JOB_HANDLER_NAME.name());
        String jobHandlerParam = context.getMergedJobDataMap().getString(JobDataKeyEnum.JOB_HANDLER_PARAM.name());

        // 执行任务
        Long jobLogId = null;
        LocalDateTime startTime = LocalDateTime.now();
        String data = null;
        Throwable exception = null;
        try {
            // 记录 Job 日志（初始）
            jobLogId = jobLogFrameworkService.createJobLog(jobId, startTime, jobHandlerName, jobHandlerParam);
            // 执行任务
            data = this.executeInternal(jobHandlerName, jobHandlerParam);
        } catch (Throwable ex) {
            exception = ex;
        }

        // 记录执行日志
        this.updateJobLogResultAsync(jobLogId, startTime, data, exception, context);

        // 处理有异常的情况
        handleException(exception);
    }

    private String executeInternal(String jobHandlerName, String jobHandlerParam) throws Exception {
        // 获得 JobHandler 对象
        JobHandler jobHandler = applicationContext.getBean(jobHandlerName, JobHandler.class);
        Assert.notNull(jobHandler, "JobHandler 不能为空");
        // 执行任务
        return jobHandler.execute(jobHandlerParam);
    }

    private void updateJobLogResultAsync(Long jobLogId, LocalDateTime startTime, String data, Throwable exception, JobExecutionContext context) {
        LocalDateTime endTime = LocalDateTime.now();
        // 处理是否成功
        boolean success = exception == null;
        if (!success) {
            data = getRootCauseMessage(exception);
        }
        // 更新日志
        try {
            jobLogFrameworkService.updateJobLogResultAsync(jobLogId, endTime,
                    (int) LocalDateTimeUtil.between(startTime, endTime).toMillis(), success, data);
        } catch (Exception ex) {
            log.error("[executeInternal][Job({}) logId({}) 记录执行日志失败({}/{})]",
                    context.getJobDetail().getKey(), jobLogId, success, data);
        }
    }

    private void handleException(Throwable exception) throws JobExecutionException {
        // 如果有异常，则进行重试
        if (exception == null) {
            return;
        }

        throw new JobExecutionException(exception);
    }

}
