package com.evans.project.quartz.handler;

/**
 * @author Evans
 * @date 2026/3/17
 */
public interface JobHandler {

    /**
     * 执行任务
     *
     * @param param 参数
     * @return 结果
     * @throws Exception 异常
     */
    String execute(String param) throws Exception;

}
