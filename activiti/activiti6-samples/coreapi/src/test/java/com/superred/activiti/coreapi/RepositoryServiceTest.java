package com.superred.activiti.coreapi;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RepositoryServiceTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 发布流程，查询发布
     */
    @Test
    public void testRepository(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name("测试部署资源1")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");

        Deployment deploy = deploymentBuilder.deploy();
        LOGGER.info("deploy = {}",deploy);

        DeploymentBuilder deployment1 = repositoryService.createDeployment();
        deployment1.name("测试部署资源2")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");

        deployment1.deploy();

        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> deploymentList = deploymentQuery
//                .deploymentId(deploy.getId())
                .orderByDeploymenTime().asc()
                .list();
        for (Deployment deployment : deploymentList) {
            LOGGER.info("deployment = {}",deployment);
        }
        LOGGER.info("deployment.size = {}",deploymentList.size());

        List<ProcessDefinition> list = repositoryService
                .createProcessDefinitionQuery()
//                .deploymentId(deployment.getId())
                .orderByProcessDefinitionKey().asc()
                .list();
        for (ProcessDefinition processDefinition : list) {
            LOGGER.info("processDefinition = {},veriosn = {} ,key = {},id ={}",
                    processDefinition,
                    processDefinition.getVersion(),
                    processDefinition.getKey(),
                    processDefinition.getId()
                    );

        }
    }

    /**
     * 暂停挂起、激活、启动流程定义
     */
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testSuspend(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();

        LOGGER.info("processDefinition.id = {}",processDefinition.getId());

        repositoryService.suspendProcessDefinitionById(processDefinition.getId());

        try {
            LOGGER.info("开始启动");
            activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
            LOGGER.info("启动成功");
        } catch (Exception e) {
            LOGGER.info("启动失败");
            LOGGER.info(e.getMessage(),e);
        }
        repositoryService.activateProcessDefinitionById(processDefinition.getId());

        LOGGER.info("开始启动");
        activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
        LOGGER.info("启动成功");
    }

    /**
     * 流程定义与用户关联时使用
     */
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCandidateStarter(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        LOGGER.info("processDefinition.id = {}",processDefinition.getId());
        repositoryService.addCandidateStarterUser(processDefinition.getId(),"user");
        repositoryService.addCandidateStarterGroup(processDefinition.getId(),"group");

        List<IdentityLink> identityLinks = repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
        for (IdentityLink identityLink : identityLinks) {
            LOGGER.info("identityLink = {}",identityLink);
        }

        repositoryService.deleteCandidateStarterGroup(processDefinition.getId(),"group");
        repositoryService.deleteCandidateStarterUser(processDefinition.getId(),"user");
    }

}
