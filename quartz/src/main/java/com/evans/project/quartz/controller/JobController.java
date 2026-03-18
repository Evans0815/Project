package com.evans.project.quartz.controller;

import com.evans.project.common.entity.ResultBody;
import com.evans.project.common.pojo.PageResult;
import com.evans.project.common.util.object.BeanUtils;
import com.evans.project.quartz.entity.dataobject.JobDO;
import com.evans.project.quartz.entity.vo.JobPageReqVO;
import com.evans.project.quartz.entity.vo.JobRespVO;
import com.evans.project.quartz.entity.vo.JobSaveReqVO;
import com.evans.project.quartz.service.JobService;
import com.evans.project.quartz.util.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Validated
@RestController
@RequestMapping("job")
public class JobController {

    @Resource
    private JobService jobService;

    @PostMapping("create")
    public ResultBody<Long> createJob(@Validated @RequestBody JobSaveReqVO createReqVO) throws SchedulerException {
        return ResultBody.ok(jobService.createJob(createReqVO));
    }

    @PutMapping("update")
    public ResultBody<Void> updateJob(@Validated @RequestBody JobSaveReqVO updateReqVO) throws SchedulerException {
        jobService.updateJob(updateReqVO);
        return ResultBody.ok();
    }

    @PutMapping("update-status")
    public ResultBody<Void> updateJobStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status)
            throws SchedulerException {
        jobService.updateJobStatus(id, status);
        return ResultBody.ok();
    }

    @DeleteMapping("delete")
    public ResultBody<Void> deleteJob(@RequestParam("id") Long id) throws SchedulerException {
        jobService.deleteJob(id);
        return ResultBody.ok();
    }

    @DeleteMapping("delete-list")
    public ResultBody<Void> deleteJobList(@RequestParam("ids") List<Long> ids) throws SchedulerException {
        jobService.deleteJobList(ids);
        return ResultBody.ok();
    }

    @PutMapping("trigger")
    public ResultBody<Void> triggerJob(@RequestParam("id") Long id) throws SchedulerException {
        jobService.triggerJob(id);
        return ResultBody.ok();
    }

    @PostMapping("sync")
    public ResultBody<Void> syncJob() throws SchedulerException {
        jobService.syncJob();
        return ResultBody.ok();
    }

    @GetMapping("get")
    public ResultBody<JobRespVO> getJob(@RequestParam("id") Long id) {
        JobDO job = jobService.getJob(id);
        return ResultBody.ok(BeanUtils.toBean(job, JobRespVO.class));
    }

    @GetMapping("get-page")
    public ResultBody<PageResult<JobRespVO>> getJobPage(@Validated JobPageReqVO pageReqVO) {
        PageResult<JobDO> jobPage = jobService.getJobPage(pageReqVO);
        return ResultBody.ok(BeanUtils.toBean(jobPage, JobRespVO.class));
    }

    /**
     * 获取定时任务下 n 次的执行时间
     */
    @GetMapping("get-next-times")
    public ResultBody<List<LocalDateTime>> getNextTimes(@RequestParam("id") Long id,
                                                        @RequestParam(value = "count", required = false, defaultValue = "5") Integer count) {
        JobDO job = jobService.getJob(id);
        List<LocalDateTime> nextTimes = job == null ? Collections.emptyList() : CronUtils.getNextTimes(job.getCronExpression(), count);
        return ResultBody.ok(nextTimes);
    }

}
