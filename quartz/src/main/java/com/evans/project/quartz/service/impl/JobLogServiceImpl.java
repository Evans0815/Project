package com.evans.project.quartz.service.impl;

import com.evans.project.common.pojo.PageResult;
import com.evans.project.quartz.entity.dataobject.JobLogDO;
import com.evans.project.quartz.entity.enums.JobLogStatusEnum;
import com.evans.project.quartz.entity.vo.JobLogPageReqVO;
import com.evans.project.quartz.mapper.JobLogMapper;
import com.evans.project.quartz.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Slf4j
@Service
@Validated
public class JobLogServiceImpl implements JobLogService {

    @Resource
    private JobLogMapper jobLogMapper;

    @Override
    public Long createJobLog(Long jobId, LocalDateTime beginTime, String jobHandlerName, String jobHandlerParam) {
        JobLogDO jobLog = JobLogDO.builder()
                .jobId(jobId)
                .handlerName(jobHandlerName)
                .handlerParam(jobHandlerParam)
                .beginTime(beginTime)
                .status(JobLogStatusEnum.RUNNING.getStatus())
                .build();
        jobLogMapper.insert(jobLog);
        return jobLog.getId();
    }

    @Async
    @Override
    public void updateJobLogResultAsync(Long logId, LocalDateTime endTime, Integer duration, boolean success, String result) {
        try {
            JobLogDO updateObj = JobLogDO.builder()
                    .id(logId)
                    .endTime(endTime)
                    .duration(duration)
                    .status(success ? JobLogStatusEnum.SUCCESS.getStatus() : JobLogStatusEnum.FAILURE.getStatus())
                    .result(result)
                    .build();
            jobLogMapper.updateById(updateObj);
        } catch (Exception ex) {
            log.error("[updateJobLogResultAsync][logId({}) endTime({}) duration({}) success({}) result({})]",
                    logId, endTime, duration, success, result);
        }
    }

    @Override
    public JobLogDO getJobLog(Long id) {
        return jobLogMapper.selectById(id);
    }

    @Override
    public PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO) {
        return jobLogMapper.selectPage(pageReqVO);
    }

    @Override
    public Integer cleanJobLog(Integer exceedDay, Integer deleteLimit) {
        int count = 0;
        LocalDateTime expireDate = LocalDateTime.now().minusDays(exceedDay);
        // 循环删除，直到没有满足条件的数据
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            int deleteCount = jobLogMapper.deleteByCreateTimeLt(expireDate, deleteLimit);
            count += deleteCount;
            // 达到删除预期条数，说明到底了
            if (deleteCount < deleteLimit) {
                break;
            }
        }
        return count;
    }

}
