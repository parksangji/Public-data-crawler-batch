package com.example.datacrawler.batch.processor;

import com.example.datacrawler.dto.ExchangeRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExchangeRateProcessor implements ItemProcessor<List<ExchangeRateDto>, List<ExchangeRateDto>> {

    @Override
    public List<ExchangeRateDto> process(List<ExchangeRateDto> items) throws Exception {
        log.info("Processing {} exchange rate items.", items.size());
        return items;
    }
}