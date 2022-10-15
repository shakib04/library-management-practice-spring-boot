package com.brac.its.libraryManagement.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.UUID;

@RestController
@Log4j2
public class SurokkhaAPITestResource {

    static long successCount = 0;
    static long successCount_signUp = 0;
    static long failedCount = 0;
    static long failedCount_signUp = 0;

    @GetMapping("/api/testing/surokkha")
    public void testRealCashDepositPost(){

        RestTemplate restTemplate = new RestTemplate();


        RestTemplate template = new RestTemplate();
        CreateObjectInput payload = new CreateObjectInput();
        payload.nid = "19981219447000136";
        payload.dob = "11/30/1998";
        payload.passport_no = false;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.IkxJVkUi.FPCKiT3oLsG_LN03a4ea8ZGSO3Drxe6jK70skLWaddM");

        HttpEntity<?> requestEntity =
                new HttpEntity<>(payload, headers);


        for (int i = 0; i < 1; i++) {

            try {
                Object response =
                        template.exchange("https://surokkha.gov.bd/manage/api/get-certificate", HttpMethod.POST, requestEntity,
                                Object.class);

                successCount++;
                log.info("[Surokkha] sent successfully, request no: {}", successCount);
            }catch (Exception exception){
                log.error(exception);
                failedCount++;
                log.info("failed no: {}", failedCount);
            }
        }

        log.info("Total sent successfully: {}", successCount);
        log.info("Total failed: {}", failedCount);

        //{"method":"4","to":"BK 01784486762","amount":"2197","from":"26","transaction_id":"324234234234","user_id":"5135","method_id":"4"}
    }
}

class CreateObjectInput{
    public String nid;
    public String dob;
    public boolean passport_no;
}