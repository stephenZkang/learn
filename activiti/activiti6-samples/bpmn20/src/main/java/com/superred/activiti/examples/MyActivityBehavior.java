package com.superred.activiti.examples;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyActivityBehavior implements ActivityBehavior {

    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(MyActivityBehavior.class);

    @Override
    public void execute(DelegateExecution execution) {
        LOGGER.info("run my activity behavior");

    }
}
