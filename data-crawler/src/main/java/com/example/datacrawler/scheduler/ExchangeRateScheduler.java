package com.example.datacrawler.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeRateScheduler {
    private final JobLauncher jobLauncher;
    private final Job exchangeRateCrawlJob;

    @Value("${crawler.schedule.enabled}")
    private boolean schedulerEnabled;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "${crawler.schedule.cron}")
    public void runExchangeRateCrawlJob() {
        if (schedulerEnabled) {
            log.info("Exchange rate crawl job started at {}", LocalDateTime.now().format(FORMATTER));
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addString("currentTime", String.valueOf(System.currentTimeMillis()))
                        .toJobParameters();
                jobLauncher.run(exchangeRateCrawlJob, jobParameters);
                log.info("Exchange rate crawl job finished at {}", LocalDateTime.now().format(FORMATTER));
            } catch (Exception e) {
                log.error("Error while running exchange rate crawl job: {}", e.getMessage());
            }
        } else {
            log.info("Exchange rate crawl scheduler is disabled.");
        }
    }
}