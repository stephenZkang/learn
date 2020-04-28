package com.superred.activiti.examples;

import com.google.common.collect.Lists;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTaskListener implements TaskListener {

    /** logger **/
    private static final Logger LOGGER = LoggerFactory.getLogger(MyTaskListener.class);


    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        if(StringUtils.equals("create",eventName)){
            LOGGER.info("config by listener");

            delegateTask.addCandidateUsers(Lists.newArrayList("user1","user2"));
            delegateTask.addCandidateGroup("group1");
            delegateTask.setVariable("key1","value1");

            delegateTask.setDueDate(DateTime.now().plusDays(3).toDate());
        }else if(StringUtils.equals("complete",eventName)){
            LOGGER.info("task complete");
        }

    }
}
