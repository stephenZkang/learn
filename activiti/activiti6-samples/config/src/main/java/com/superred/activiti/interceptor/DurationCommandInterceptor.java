package com.superred.activiti.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurationCommandInterceptor extends AbstractCommandInterceptor {

    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(DurationCommandInterceptor.class);

    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            return this.getNext().execute(config,command);
        }finally {
            long duration = System.currentTimeMillis() - start;
            LOGGER.info("{} 执行时长 {} 毫秒",command.getClass(),duration);
        }
    }
}
