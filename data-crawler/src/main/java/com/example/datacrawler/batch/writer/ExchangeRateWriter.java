package com.example.datacrawler.batch.writer;

import com.example.datacrawler.dto.ExchangeRateDto;
import com.example.datacrawler.entity.ExchangeRate;
import com.example.datacrawler.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeRateWriter implements ItemWriter<List<ExchangeRateDto>> {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public void write(Chunk<? extends List<ExchangeRateDto>> chunk) throws Exception {
        List<? extends List<ExchangeRateDto>> items = chunk.getItems();
        if (!items.isEmpty()) {
            List<ExchangeRateDto> exchangeRates = items.get(0);
            List<ExchangeRate> entities = exchangeRates.stream()
                    .map(dto -> {
                        ExchangeRate entity = new ExchangeRate();
                        entity.setCurUnit(dto.getCurUnit());
                        entity.setCurNm(dto.getCurNm());
                        entity.setTtb(dto.getTtb());
                        entity.setTts(dto.getTts());
                        entity.setDealBasR(dto.getDealBasR());
                        entity.setBkpr(dto.getBkpr());
                        entity.setYyEfeeR(dto.getYyEfeeR());
                        entity.setTenDdEfeeR(dto.getTenDdEfeeR());
                        entity.setKftcDealBasR(dto.getKftcDealBasR());
                        entity.setKftcBkpr(dto.getKftcBkpr());
                        entity.setCreatedAt(LocalDateTime.now());
                        return entity;
                    })
                    .collect(Collectors.toList());
            exchangeRateRepository.saveAll(entities);
            log.info("Successfully saved {} exchange rate entities.", entities.size());
        } else {
            log.info("No exchange rate data to write to the database.");
        }
    }
}
