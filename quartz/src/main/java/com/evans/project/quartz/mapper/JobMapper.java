package com.evans.project.quartz.mapper;

import com.evans.project.common.pojo.PageResult;
import com.evans.project.database.mapper.BaseMapperX;
import com.evans.project.database.query.LambdaQueryWrapperX;
import com.evans.project.quartz.entity.dataobject.JobDO;
import com.evans.project.quartz.entity.vo.JobPageReqVO;

/**
 * @author Evans
 * @date 2026/3/17
 */
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
                .orderByDesc(JobDO::getId));
    }

}
