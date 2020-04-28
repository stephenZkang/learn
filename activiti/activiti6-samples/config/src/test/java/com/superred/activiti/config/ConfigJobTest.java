package com.superred.activiti.config;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 事件日志测试
 */
public class ConfigJobTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigJobTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job.cfg.xml");

    @Test
    @Deployment(resources = {"com/superred/activiti/my-process_job.bpmn20.xml"})
    public void test() throws InterruptedException {
        LOGGER.info("start");
        List<Job> jobList = activitiRule.getManagementService().createTimerJobQuery().list();
        for (Job job : jobList) {
            LOGGER.info("定时任务 = {}， 默认重试次数 = {}",job,job.getRetries());
        }
        LOGGER.info("jobList.size = {}",jobList.size());
        Thread.sleep(10*1000);
        LOGGER.info("end");
     }
}
