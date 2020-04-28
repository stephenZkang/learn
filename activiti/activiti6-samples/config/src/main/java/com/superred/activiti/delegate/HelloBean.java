package com.superred.activiti.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloBean {
    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBean.class);


    public void sayHello(){
        LOGGER.info("say Hello");

    }
}
