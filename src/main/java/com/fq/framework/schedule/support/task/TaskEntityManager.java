package com.fq.framework.schedule.support.task;

import com.fq.framework.schedule.domain.TaskInfo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import javax.annotation.Resource;

/**
 * @author wangxiaohong
 */
public class TaskEntityManager implements ITaskEntityManager {

    @Resource
    private Scheduler scheduler;

    @Resource
    private TaskInfoChecker taskInfoChecker;

    @Resource
    private ITaskJobGroupFactory jobGroupFactory;


    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void startup() throws SchedulerException {
        this.scheduler.start();
    }

    public void startup(TaskInfo taskInfo) {
        try {
            JobGroup jobGroup = jobGroupFactory.getJobGroup(taskInfo);
            if (scheduler.checkExists(jobGroup.getJobKey())) {
                scheduler.deleteJob(jobGroup.getJobKey());
            }
            scheduler.scheduleJob(jobGroup.getJobDetail(), jobGroup.getTrigger());

        } catch (SchedulerException s) {
            throw new RuntimeException("启动失败[" + s.getMessage() + "]", s);
        }
    }


    public void pause(TaskInfo taskInfo) {
        JobGroup jobGroup = jobGroupFactory.getJobGroup(taskInfo);
        try {
            if (scheduler.checkExists(jobGroup.getJobKey())) {
                scheduler.pauseJob(jobGroup.getJobKey());
            }
        } catch (Exception e) {
            throw new RuntimeException("暂停失败[" + e.getMessage() + "]", e);
        }

    }

    @Override
    public void delete(TaskInfo taskInfo) {
        JobGroup jobGroup = jobGroupFactory.getJobGroup(taskInfo);
        try {
            if (scheduler.checkExists(jobGroup.getJobKey())) {
                scheduler.deleteJob(jobGroup.getJobKey());
            }
        } catch (Exception e) {
            throw new RuntimeException("删除失败[" + e.getMessage() + "]", e);
        }

    }
}
