package com.fq.framework.schedule.support.task;


import com.fq.framework.schedule.domain.TaskInfo;

/**
 * User: Administrator
 * Date: 14-2-14
 * Time: 上午10:14
 */
public interface ITaskJobGroupFactory {
    public JobGroup getJobGroup(TaskInfo taskInfo);
}
