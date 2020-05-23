package com.example.user.service.server.scheduler.service.impl;


import com.example.user.service.server.entity.User;
import com.example.user.service.server.scheduler.job.SampleJob;
import com.example.user.service.server.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by surender.s on 11/12/17.
 */
@Service
@Slf4j
public class SampleSchedulerService implements SchedulerService<User> {

    @Inject
    SchedulerFactoryBean schedulerFactory;

    @Override
    public void clearAllJob() throws Exception {
        try {
            schedulerFactory.getScheduler().clear();
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @Override
    public void registerNewJob(User user, int frequencyIntervalInMinutes) throws Exception {
        try {
            schedulerFactory.getScheduler().scheduleJob(getJobDetail(user), Collections.singleton(getTrigger(user,
                    frequencyIntervalInMinutes)), true);
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @Override
    public void deleteJob(User user) throws Exception {
        try {
            schedulerFactory.getScheduler().deleteJob(JobKey.jobKey(getName(user, SampleJob.JOB),
                    SampleJob.TRIGGER_GROUP));
        } catch (Exception e) {
            throw new Exception("Exception Occurred: ", e);
        }
    }

    private JobDetail getJobDetail(User user) throws Exception {
        return newJob(SampleJob.class)
                .withIdentity(getName(user, SampleJob.JOB), SampleJob.JOB_GROUP)
                .usingJobData(SampleJob.USER_ID, String.valueOf(user.getId()))
                .storeDurably().build();
    }

    private String getName(User user, String prefix) {
        return String.format("%s_%s", prefix, String.valueOf(user.getId()));
    }

    private Trigger getTrigger(User user, int frequencyIntervalInMinutes) {
        return TriggerBuilder.newTrigger()
                .withIdentity(getName(user, SampleJob.TRIGGER), SampleJob.JOB_GROUP)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .repeatForever()
                    .withIntervalInMinutes(frequencyIntervalInMinutes))
                .build();
    }
}
