package com.example.datacrawler.batch.writer;

import com.example.datacrawler.dto.ExchangeRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExchangeRateWriter implements ItemWriter<List<ExchangeRateDto>> {
    @Override
    public void write(Chunk<? extends List<ExchangeRateDto>> chunk) throws Exception {
        List<? extends List<ExchangeRateDto>> items = chunk.getItems();
        if (!items.isEmpty()) {
            List<ExchangeRateDto> exchangeRates = items.get(0);
            for (ExchangeRateDto rate : exchangeRates) {
                log.info("Writing exchange rate: {}", rate);
            }
        } else {
            log.info("No exchange rate data to write.");
        }
    }
}
