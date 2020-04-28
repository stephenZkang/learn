package com.superred.activiti.bpmn20;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TimerEventTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerEventTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process-timer-boundary.bpmn20.xml"})
    public void testTimerBoundary() throws InterruptedException {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");

        List<Task> tasks = activitiRule.getTaskService().createTaskQuery().list();
        for (Task task : tasks) {
            LOGGER.info("task.name = {}",task.getName());
        }

        LOGGER.info("tasks.size = {}",tasks.size());

        Thread.sleep(1000*15);

        tasks = activitiRule.getTaskService().createTaskQuery().list();
        for (Task task : tasks) {
            LOGGER.info("task.name = {}",task.getName());
        }

        LOGGER.info("tasks.size = {}",tasks.size());



    }

}
