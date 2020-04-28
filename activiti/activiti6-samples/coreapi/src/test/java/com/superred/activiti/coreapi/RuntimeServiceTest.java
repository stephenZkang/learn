package com.superred.activiti.coreapi;

import com.google.common.collect.Maps;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RuntimeServiceTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcess(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);

    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcessById(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processDefinition.getId(), variables);
        LOGGER.info("processInstance = {}",processInstance);

    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessInstanceBuilder(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        ProcessInstance processInstance = processInstanceBuilder
                .processDefinitionKey("my-process")
                .variables(variables)
                .start();

//        ProcessInstance processInstance = runtimeService
//                .startProcessInstanceById(processDefinition.getId(), variables);
        LOGGER.info("processInstance = {}",processInstance);
    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testVariables(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        variables.put("key2","value2");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);

        runtimeService.setVariable(processInstance.getId(),"key3","value3");
        Map<String, Object> variables1 = runtimeService.getVariables(processInstance.getId());
        LOGGER.info("variables1 = {}",variables1);
    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessInstanceQuery(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String,Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);

        ProcessInstance processInstance1 = runtimeService.
                createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        LOGGER.info("processInstance = {}",processInstance);
    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testExecutionQuery(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String,Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);

        List<Execution> processInstance1 = runtimeService.
                createExecutionQuery().processInstanceId(processInstance.getId()).list();
        for (Execution execution : processInstance1) {
            LOGGER.info("execution = {}",execution);
        }
    }

    @Test
    @Deployment(resources = {"my-process_trigger.bpmn20.xml"})
    public void testTrigger(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process");
        Execution someTask = runtimeService.createExecutionQuery()
                .activityId("someTask").singleResult();
        LOGGER.info("someTask = {}",someTask);

        runtimeService.trigger(someTask.getId());

        someTask = runtimeService.createExecutionQuery().activityId("someTask").singleResult();
        LOGGER.info("someTask = {}",someTask);

    }

    @Test
    @Deployment(resources = {"my-process_received.bpmn20.xml"})
    public void TestSign(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution execution = runtimeService.createExecutionQuery().signalEventSubscriptionName("my-signal").singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.signalEventReceived("my-signal");

        execution = runtimeService.createExecutionQuery().signalEventSubscriptionName("my-signal").singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.signalEventReceived("my-signal");
    }

    @Test
    @Deployment(resources = {"my-process-message-received.bpmn20.xml"})
    public void TestMessage(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.messageEventReceived("my-message",execution.getId());

        execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        LOGGER.info("execution = {}",execution);
    }

    @Test
    @Deployment(resources = {"my-process-message.bpmn20.xml"})
    public void TestMessageStart(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService
//                .startProcessInstanceByKey("my-process");
                .startProcessInstanceByMessage("my-message");
        LOGGER.info("processInstance = {}",processInstance);
    }
}
