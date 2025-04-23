package com.example.datacrawler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * RESULT	        Integer	조회 결과	1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감
 * CUR_UNIT	        String	통화코드
 * CUR_NM	        String	국가/통화명
 * TTB	            String	전신환(송금) 받으실때
 * TTS	            String	전신환(송금) 보내실때
 * DEAL_BAS_R	    String	매매 기준율
 * BKPR	            String	장부가격
 * YY_EFEE_R	    String	년환가료율
 * TEN_DD_EFEE_R	String	10일환가료율
 * KFTC_DEAL_BAS_R	String	서울외국환중개 매매기준율
 * KFTC_BKPR	    String	서울외국환중개 장부가격
 * **/

@Data
public class ExchangeRateDto {
    @JsonProperty("result")
    private Integer result;

    @JsonProperty("cur_unit")
    private String curUnit;

    @JsonProperty("cur_nm")
    private String curNm;

    @JsonProperty("ttb")
    private String ttb;

    @JsonProperty("tts")
    private String tts;

    @JsonProperty("deal_bas_r")
    private String dealBasR;

    @JsonProperty("bkpr")
    private String bkpr;

    @JsonProperty("yy_efee_r")
    private String yyEfeeR;

    @JsonProperty("ten_dd_efee_r")
    private String tenDdEfeeR;

    @JsonProperty("kftc_deal_bas_r")
    private String kftcDealBasR;

    @JsonProperty("kftc_bkpr")
    private String kftcBkpr;
}
