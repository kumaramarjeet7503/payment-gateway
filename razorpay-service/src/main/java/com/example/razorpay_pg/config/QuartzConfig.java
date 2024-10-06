package com.example.razorpay_pg.config;

import com.example.razorpay_pg.task.RazorPayJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(RazorPayJob.class)
                .withIdentity("razorpayJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger")
                .forJob(jobDetail())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }
}


