package com.example.datacrawler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String curUnit;
    private String curNm;
    private String ttb;
    private String tts;
    private String dealBasR;
    private String bkpr;
    private String yyEfeeR;
    private String tenDdEfeeR;
    private String kftcDealBasR;
    private String kftcBkpr;
    private LocalDateTime createdAt;
}
