package com.superred.activiti.coreapi;

import com.google.common.collect.Maps;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.*;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 任务服务测试
 */
public class TaskServiceTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTask(){
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process",variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}",task.getDescription());

        taskService.setVariable(task.getId(),"key1","value1");
        taskService.setVariableLocal(task.getId(),"localKey1","localValue1");
        Map<String, Object> taskServiceVariables = taskService.getVariables(task.getId());
        Map<String, Object> taskServiceVariablesLocal = taskService.getVariablesLocal(task.getId());
        Map<String, Object> processVariables = activitiRule.getRuntimeService().getVariables(task.getExecutionId());

        LOGGER.info("taskServiceVariables = {}",taskServiceVariables);
        LOGGER.info("taskServiceVariablesLocal = {}",taskServiceVariablesLocal);
        LOGGER.info("processVariables = {}",processVariables);

        Map<String,Object> completeVar = Maps.newHashMap();
        completeVar.put("cKey1","cValue1");
        taskService.complete(task.getId(),completeVar);

        Task task1 = taskService.createTaskQuery().taskId(task.getId()).singleResult();
        LOGGER.info("task1 = {}",task1);
    }

    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskServiceUser(){
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process",variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}",task.getDescription());

        taskService.setOwner(task.getId(),"user1");
//        taskService.setAssignee(task.getId(),"jimmy");

        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateUser("jimmy")
                .taskUnassigned().list();
        for (Task task1 : taskList) {
            try {
                taskService.claim(task1.getId(),"jimmy");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<IdentityLink> identityLinksForTask = taskService
                .getIdentityLinksForTask(task.getId());
        for (IdentityLink identityLink : identityLinksForTask) {
             LOGGER.info("identityLink = {}",identityLink);
        }
        List<Task> jimmys = taskService
                .createTaskQuery()
                .taskAssignee("jimmy").list();
        for (Task jimmy : jimmys) {
            Map<String,Object> vars = Maps.newHashMap();
            vars.put("ckey1","cvalue1");
            taskService.complete(jimmy.getId(),vars);
        }
        jimmys = taskService.createTaskQuery().taskAssignee("jimmy").list();
        LOGGER.info("是否存在 = {}", CollectionUtils.isEmpty(jimmys));

    }

    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskAttachment(){
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process",variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();

        taskService.createAttachment("url",task.getId(),
                task.getProcessInstanceId(),"name",
                "desc","/url/test.png");

        List<Attachment> taskAttachments = taskService.getTaskAttachments(task.getId());
        for (Attachment taskAttachment : taskAttachments) {
            LOGGER.info("taskAttachment = {}",
                    ToStringBuilder.reflectionToString(taskAttachment,ToStringStyle.JSON_STYLE));
        }

    }

    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskComment(){
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process",variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        taskService.setOwner(task.getId(),"user1");
        taskService.setAssignee(task.getId(),"jimmy");
        taskService.addComment(task.getId(),task.getProcessInstanceId(),"tttt");
        taskService.addComment(task.getId(),task.getProcessInstanceId(),"tttt2");

        List<Comment> taskComments = taskService.getTaskComments(task.getId());
        for (Comment taskComment : taskComments) {
            LOGGER.info("taskComment = {}",ToStringBuilder.reflectionToString(taskComment,ToStringStyle.JSON_STYLE));
        }

        List<Event> taskEvents = taskService.getTaskEvents(task.getId());
        for (Event taskEvent : taskEvents) {
            LOGGER.info("taskEvent = {}",ToStringBuilder.reflectionToString(taskEvent,ToStringStyle.JSON_STYLE));
        }
    }



}
