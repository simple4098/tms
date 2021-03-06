package com.fq.framework.schedule.web;

import com.fq.framework.schedule.support.task.ITaskEntityManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 容器 启动 监听 , 目前主要的功能是启动 调度器
 *
 * @author wangxiaohong
 */
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
            ITaskEntityManager taskEntityManager = webApplicationContext.getBean(ITaskEntityManager.class);
            taskEntityManager.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
