package com.example.datacrawler.batch;

import com.example.datacrawler.batch.processor.ExchangeRateProcessor;
import com.example.datacrawler.batch.reader.ExchangeRateReader;
import com.example.datacrawler.batch.writer.ExchangeRateWriter;
import com.example.datacrawler.dto.ExchangeRateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class ExchangeRateBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ExchangeRateReader exchangeRateReader;
    private final ExchangeRateProcessor exchangeRateProcessor;
    private final ExchangeRateWriter exchangeRateWriter;

    @Bean
    public Step crawlExchangeRateStep() {
        return new StepBuilder("crawlExchangeRateStep", jobRepository)
                .<List<ExchangeRateDto>, List<ExchangeRateDto>>chunk(1)
                .reader(exchangeRateReader)
                .processor(exchangeRateProcessor)
                .writer(exchangeRateWriter)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job exchangeRateCrawlJob() {
        return new JobBuilder("exchangeRateCrawlJob", jobRepository)
                .start(crawlExchangeRateStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
