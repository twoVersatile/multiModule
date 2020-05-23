package com.example.user.service.server.scheduler.job;

import com.example.user.manager.datatypes.UserState;
import com.example.user.service.server.entity.User;
import com.example.user.service.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by surender.s on 11/12/17.
 */
@Slf4j
@DisallowConcurrentExecution
@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class SampleJob extends QuartzJobBean {

    public static final String JOB = "SAMPLE_JOB";
    public static final String JOB_GROUP = "SAMPLE_JOB_GROUP";
    public static final String TRIGGER = "SAMPLE_TRIGGER";
    public static final String TRIGGER_GROUP = "SAMPLE_TRIGGER_GROUP";
    public static final String USER_ID = "USER_ID";

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

            Long userId = Long.parseLong(jobDataMap.getString(USER_ID));

            User user = userRepository.findOne(userId);

            log.info("Job Ran for this user: " + userId);
            log.info(user.toString());

            user.setUserState(UserState.ACTIVE);

            userRepository.save(user);

        } catch (Exception exception) {
            log.error("Exception occurred while executing Job:", exception);
        }
    }
}
