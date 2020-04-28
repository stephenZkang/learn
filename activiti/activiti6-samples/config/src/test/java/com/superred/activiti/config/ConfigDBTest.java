package com.superred.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库配置
 */
public class ConfigDBTest {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDBTest.class);

    @Test
    public void testConfig1(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResourceDefault();

        LOGGER.info("configuration = {}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("流程引擎 {}",processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfig2(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_druid.cfg.xml");

        LOGGER.info("configuration = {}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("流程引擎 {}",processEngine.getName());
        processEngine.close();
    }
}
