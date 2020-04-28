package com.superred.activiti.coreapi;

import com.superred.activiti.mapper.MyCustomMapper;
import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.management.TablePage;
import org.activiti.engine.management.TablePageQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ManagerServiceTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-job.cfg.xml");

    @Test
    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    public void testJobQuery(){
        ManagementService managementService = activitiRule.getManagementService();

        List<Job> timerList = managementService.createTimerJobQuery().list();
        for (Job timerJob : timerList) {
            LOGGER.info("timerJob = {}",timerJob);
        }

        JobQuery jobQuery = managementService.createJobQuery();

        List<Job> suspendedJobList = managementService.createSuspendedJobQuery().list();
        for (Job job : suspendedJobList) {
            LOGGER.info("suspendedJob = {}",job);
        }

        List<Job> deadLetterJobList = managementService.createDeadLetterJobQuery().list();
        for (Job deadLetterJob : deadLetterJobList) {
            LOGGER.info("deadLetterJob = {}",deadLetterJob);
        }
    }

    @Test
    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    public void testTablePageQuery(){
        ManagementService managementService = activitiRule.getManagementService();
        TablePage tablePage = managementService.createTablePageQuery().tableName(managementService.getTableName(ProcessDefinition.class)).listPage(0, 100);

        List<Map<String, Object>> rows = tablePage.getRows();
        for (Map<String, Object> row : rows) {
            LOGGER.info("row = {}",row);
        }
    }

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCustomSql(){
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();
        List<Map<String, Object>> mapList = managementService.executeCustomSql(new AbstractCustomSqlExecution<MyCustomMapper, List<Map<String, Object>>>(MyCustomMapper.class) {

            @Override
            public List<Map<String, Object>> execute(MyCustomMapper o) {
                return o.findAll();
            }
        });

        for (Map<String, Object> map : mapList) {
            LOGGER.info("map = {}",map);
        }
    }
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCustomCommand(){
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();

        ProcessDefinitionEntity processDefinitionEntity = managementService.executeCommand(new Command<ProcessDefinitionEntity>() {
            @Override
            public ProcessDefinitionEntity execute(CommandContext commandContext) {
                ProcessDefinitionEntity processDefinitionEntity = commandContext.getProcessDefinitionEntityManager()
                        .findLatestProcessDefinitionByKey("my-process");
                return processDefinitionEntity;
            }
        });


        LOGGER.info("processDefinitionEntity = {}",processDefinitionEntity);
    }


}
