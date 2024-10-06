package com.example.razorpay_pg.task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void reportCurrentTime() {
        System.out.println("Current time: " + System.currentTimeMillis());
    }

    @Scheduled(cron = "0 * * * * ?") // Run every minute at the start of the minute
    public void performTaskUsingCron() {
        System.out.println("Cron task performed at: " + System.currentTimeMillis());
    }
}
