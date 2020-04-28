package com.superred.activiti.config;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Spring测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:activiti-context.xml"})
public class ConfigSpringtest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSpringtest.class);

    @Rule
    @Autowired
    public ActivitiRule activitiRule;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    @Deployment(resources = {"com/superred/activiti/my-process_spring.bpmn20.xml"})
    public void test(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Task task = taskService.createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

    }
}
