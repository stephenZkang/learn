package com.superred.activiti.dbentity;

import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbGeTest {

    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(DbGeTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    @Test
    public void testByteArrays(){
        activitiRule.getRepositoryService().createDeployment()
                .name("测试部署")
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();
    }

    @Test
    public void testByteArrayInsert(){
        ManagementService managementService = activitiRule.getManagementService();
        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                ByteArrayEntityImpl entity = new ByteArrayEntityImpl();
                entity.setName("test");
                entity.setBytes("test message".getBytes());
                commandContext.getByteArrayEntityManager().insert(entity);
                return null;
            }
        });
    }

}
