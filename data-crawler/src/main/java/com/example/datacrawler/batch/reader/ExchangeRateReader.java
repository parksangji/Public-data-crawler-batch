package com.example.datacrawler.batch.reader;

import com.example.datacrawler.dto.ExchangeRateDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateReader implements ItemReader<List<ExchangeRateDto>> {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${crawler.exchange.authkey}")
    private String authkey;

    @Value("${crawler.exchange.url}")
    private String apiUrl;

    private static final String DATA_TYPE = "AP01";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private boolean read = false;

    @Override
    public List<ExchangeRateDto> read() throws Exception{
        if (!read) {
            LocalDate now = LocalDate.now();
            String searchDate = now.format(DATE_FORMATTER);
            String requestUrl = String.format("%s?authkey=%s&searchdate=%s&data=%s",
                    apiUrl, authkey, searchDate, DATA_TYPE);

            log.info("Requesting exchange rates from: {}", requestUrl);

            try {
                String response = restTemplate.getForObject(requestUrl, String.class);
                log.debug("Exchange rate API response: {}", response);
                List<ExchangeRateDto> exchangeRates = objectMapper.readValue(response, new TypeReference<List<ExchangeRateDto>>() {});
                read = true; // 읽음 상태로 변경
                return exchangeRates;
            } catch (IOException e) {
                log.error("Failed to parse exchange rate API response: {}", e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("Error while fetching exchange rates: {}", e.getMessage());
                throw e;
            }
        } else {
            return null; // 더 이상 읽을 데이터가 없음을 알림
        }
    }
}
