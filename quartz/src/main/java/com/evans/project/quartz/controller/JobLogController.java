package com.evans.project.quartz.controller;

import com.evans.project.common.entity.ResultBody;
import com.evans.project.common.pojo.PageResult;
import com.evans.project.common.util.object.BeanUtils;
import com.evans.project.quartz.entity.dataobject.JobLogDO;
import com.evans.project.quartz.entity.vo.JobLogPageReqVO;
import com.evans.project.quartz.entity.vo.JobLogRespVO;
import com.evans.project.quartz.service.JobLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Evans
 * @date 2026/3/17
 */
@Validated
@RestController
@RequestMapping("job-log")
public class JobLogController {

    @Resource
    private JobLogService jobLogService;

    @GetMapping("get")
    public ResultBody<JobLogRespVO> getJobLog(@RequestParam("id") Long id) {
        JobLogDO jobLog = jobLogService.getJobLog(id);
        return ResultBody.ok(BeanUtils.toBean(jobLog, JobLogRespVO.class));
    }

    @GetMapping("page")
    public ResultBody<PageResult<JobLogRespVO>> getJobLogPage(@Valid JobLogPageReqVO pageReqVO) {
        PageResult<JobLogDO> pageResult = jobLogService.getJobLogPage(pageReqVO);
        return ResultBody.ok(BeanUtils.toBean(pageResult, JobLogRespVO.class));
    }

}
