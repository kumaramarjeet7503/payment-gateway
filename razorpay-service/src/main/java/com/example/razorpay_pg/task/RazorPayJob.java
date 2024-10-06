package com.example.razorpay_pg.task;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class RazorPayJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Executing Job..."+System.currentTimeMillis());
    }
}