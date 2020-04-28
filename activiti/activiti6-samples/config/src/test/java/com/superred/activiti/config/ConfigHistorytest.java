package com.superred.activiti.config;

import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * History Level测试
 */
public class ConfigHistorytest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHistorytest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_history.cfg.xml");

    @Test
    @Deployment(resources = {"com/superred/activiti/my-process.bpmn20.xml"})
    public void test(){

        //启动流程
        startProcess();

        //修改变量
        changeVariables();

        //提交表单 task
        submitTasks();

        //输出历史内容
        //输出历史活动
        showHistoryActivity();

        //输出历史表单
        showHistoryTasks();

        showHistoryForm();

        //输出历史详情
        showHistoryDetails();
    }

    private void showHistoryDetails() {
        List<HistoricDetail> historicDetails = activitiRule.getHistoryService()
                .createHistoricDetailQuery()
                .listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetails) {
            LOGGER.info("historicDetail = {}",historicDetail);
        }
        LOGGER.info("historicDetail.size = {}",historicDetails.size());
    }

    private void showHistoryForm() {
        List<HistoricDetail> historicDetailForms = activitiRule.getHistoryService()
                .createHistoricDetailQuery()
                .formProperties()
                .listPage(0, 100);
        for (HistoricDetail historicDetailForm : historicDetailForms) {
            LOGGER.info("historicDetailForm = {}",historicDetailForm);
        }
        LOGGER.info("historicDetailForms.size = {}",historicDetailForms.size());
    }

    private void showHistoryTasks() {
        List<HistoricTaskInstance> historicTaskInstances = activitiRule.getHistoryService().createHistoricTaskInstanceQuery()
                .listPage(0, 100);
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            LOGGER.info("historicTaskInstance = {}",historicTaskInstance);
        }
        LOGGER.info("historicTaskInstance.size = {}",historicTaskInstances.size());
    }

    private void showHistoryActivity() {
        List<HistoricActivityInstance> historicActivityInstances = activitiRule
                .getHistoryService().createNativeHistoricActivityInstanceQuery()
                .listPage(0, 100);
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            LOGGER.info("historicActivityInstance = {}",historicActivityInstance);
        }
        LOGGER.info("historicActivityInstance.size = {}",historicActivityInstances.size());
    }

    private void submitTasks() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> properties = Maps.newHashMap();
        properties.put("formKey1","valuef1");
        properties.put("formKey2","valuef2");
        activitiRule.getFormService().submitTaskFormData(task.getId(),properties);
    }

    private void changeVariables() {
        List<Execution> executions = activitiRule.getRuntimeService()
                .createExecutionQuery()
                .listPage(0, 100);
        for (Execution execution : executions) {
            LOGGER.info("execution = {}",execution);
        }
        LOGGER.info("execution.size = {}",executions.size());
        String id = executions.iterator().next().getId();
        activitiRule.getRuntimeService().setVariable(id,"keyStart1","value1_");
    }

    private void startProcess() {
        Map<String,Object> params = Maps.newHashMap();
        params.put("keyStart1","value1");
        params.put("keyStart2","value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process",params);
    }
}
