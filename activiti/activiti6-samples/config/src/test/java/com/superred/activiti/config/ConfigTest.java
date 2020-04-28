package com.superred.activiti.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigTest {

    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);

    /**
     * 根据资源文件加载会通过spring解析，依赖spring
     */
    @Test
    public void testConfig1(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration = {}",configuration);
    }

    /**
     * 独立创建的只是创造一个独立对象，不依赖spring
     */
    @Test
    public void testConfig2(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        LOGGER.info("configuration = {}",configuration);
    }
}
