package com.superred.activiti.coreapi;

import com.google.common.collect.Maps;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 历史服务测试
 */
public class HistoryServiceTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testHistory(){
        HistoryService historyService = activitiRule.getHistoryService();
        ProcessInstanceBuilder processInstanceBuilder = activitiRule.getRuntimeService().createProcessInstanceBuilder();
        Map<String,Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        variables.put("key2","value2");
        variables.put("key3","value3");

        Map<String,Object> trasientVariables = Maps.newHashMap();
        trasientVariables.put("tkey1","tValue1");

        ProcessInstance processInstance = processInstanceBuilder.processDefinitionKey("my-process")
                .variables(variables)
                .transientVariables(trasientVariables).start();

        activitiRule.getRuntimeService().setVariable(processInstance.getId(),"","");

        Task task = activitiRule.getTaskService().createTaskQuery().processDefinitionKey("my-process").singleResult();

//        activitiRule.getTaskService().complete(task.getId(),variables);
        Map<String, String> properties = Maps.newHashMap();
        properties.put("fkey1","fValue1");
        properties.put("key2","value_2_2");
        activitiRule.getFormService().submitTaskFormData(task.getId(),properties);

        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            LOGGER.info("processInstance = {}",processInstance);
        }

        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().list();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            LOGGER.info("historicActivityInstance = {}",historicActivityInstance);
        }

        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            LOGGER.info("historicTaskInstance = {}",historicTaskInstance);
        }

        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery().list();
        for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
            LOGGER.info("historicVariableInstance = {}",historicVariableInstance);
        }

        List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().list();
        for (HistoricDetail historicDetail : historicDetails) {
            LOGGER.info("historicDetail = {}",historicDetail);
        }

        ProcessInstanceHistoryLog processInstanceHistoryLog = historyService.createProcessInstanceHistoryLogQuery(processInstance.getId())
                .includeVariables()
                .includeFormProperties()
                .includeComments()
                .includeTasks()
                .includeActivities()
                .includeVariableUpdates().singleResult();

        List<HistoricData> historicData = processInstanceHistoryLog.getHistoricData();
        for (HistoricData historicDatum : historicData) {
            LOGGER.info("historicDatum = {}",historicDatum);
        }

        historyService.deleteHistoricProcessInstance(processInstance.getId());

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        LOGGER.info("historicProcessInstance = {}",historicProcessInstance);
    }


}
