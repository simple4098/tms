package com.fq.framework.schedule.web.controller;

import com.fq.framework.schedule.domain.TaskInfo;
import com.fq.framework.schedule.dto.TaskInfoDto;
import com.fq.framework.schedule.service.ITaskEntityService;
import com.fq.framework.schedule.support.Pagination;
import com.fq.framework.schedule.web.ExtMessage;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 任务操作响应
 *
 *  @author wangxiaohong
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {


    @Resource
    private ITaskEntityService taskService;

    /**
     * 加载任务列表数据
     */
    @RequestMapping("/list")
    public ExtMessage showTaskList(Integer page, Integer limit) {
        Pagination<TaskInfoDto> pagination = new Pagination<TaskInfoDto>(limit, page);
        ExtMessage extMessage = extMessageBuilder.createResults(taskService.findList(pagination));
        extMessage.setTotalCount(10);
        return extMessage;
    }

    /**
     * 任务数据更新
     *
     * @param taskInfo 表单对象
     */
    @RequestMapping("/saveEdit")
    public ExtMessage saveEdit(TaskInfo taskInfo) {
        Assert.notNull(taskInfo, "更新失败，未找到ID");
        taskService.update(taskInfo);
        return extMessageBuilder.createMessage("更新成功");

    }

    /**
     * 任务新建保存
     *
     * @param taskInfo 表单对象
     */
    @RequestMapping("/saveCreate")
    public ExtMessage saveCreate(TaskInfo taskInfo) {
        taskService.create(taskInfo);
        return extMessageBuilder.createMessage("创建成功");
    }

    /**
     * 任务启动
     *
     * @param id 任务ID
     */
    @RequestMapping("/startup")
    public ExtMessage startup(String id) {
        taskService.startup(id);
        return extMessageBuilder.createMessage("启动成功");
    }

    /**
     * 任务暂停
     *
     * @param id 任务ID
     */
    @RequestMapping("/pause")
    public ExtMessage pause(String id) {
        taskService.pause(id);
        return extMessageBuilder.createMessage("暂停成功");
    }

    /**
     * 任务删除
     *
     * @param id 任务ID
     */
    @RequestMapping("/delete")
    public ExtMessage delete(String id) {
        taskService.delete(id);
        return extMessageBuilder.createMessage("删除成功");
    }
}
