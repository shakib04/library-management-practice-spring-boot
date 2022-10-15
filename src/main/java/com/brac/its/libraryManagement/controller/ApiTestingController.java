package com.brac.its.libraryManagement.controller;

import com.brac.its.libraryManagement.model.RealCashUser;
import com.brac.its.libraryManagement.repository.RealCashUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@Log4j2
public class ApiTestingController {

    static long successCount = 0;
    static long successCount_signUp = 0;
    static long failedCount = 0;
    static long failedCount_signUp = 0;

    @Autowired
    private RealCashUserRepository realCashUserRepository;

    @GetMapping("/api/testing/realcash")
    public void testRealCashDepositPost(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Header", "header1");

        for (int i = 0; i < 10; i++) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://realcash7admin.realcash7.com/public/api/betbd-v1/deposit")
                    .queryParam("method", "4")
                    .queryParam("to", "BK 01784486762")
                    .queryParam("amount", "2199")
                    .queryParam("from", "26")
                    .queryParam("transaction_id", UUID.randomUUID())
                    .queryParam("user_id", "5135")
                    .queryParam("method_id", "4");

            HttpEntity<?> entity = new HttpEntity<>(headers);

           try {
               HttpEntity<String> response = restTemplate.exchange(
                       builder.toUriString(),
                       HttpMethod.POST,
                       entity,
                       String.class);

               successCount++;
               log.info("[RealCash7-Deposit] sent successfully, request no: {}", successCount);
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


    @GetMapping("/api/testing/signup/realcash")
    public void testRealCashSignUpPost(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Header", "header1");

        final String resourceName = "[RealCash7-SignUp]";

        for (int i = 0; i < 110; i++) {

            String username = "bettingIsHaram" + UUID.randomUUID();
            String email = UUID.randomUUID() + "@" + "bettingIsHaram.com";
            //email = "neyewo5943@pahed.com";
            String password = "Pa$$w0rd!";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://realcash7admin.realcash7.com/public/api/betbd-v1/user/registration")
                    .queryParam("full_name", UUID.randomUUID())
                    .queryParam("user_name", username)
                    .queryParam("email", email)
                    .queryParam("mobile_number", UUID.randomUUID())
                    .queryParam("club_id", "6")
                    .queryParam("sponsor_user_name", "daquti")
                    .queryParam("password_confirmation", password)
                    .queryParam("password", password);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            try {
                HttpEntity<String> response = restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.POST,
                        entity,
                        String.class);

                log.info("Request Body of Sign up: {}", builder.toUriString());
                successCount_signUp++;
                log.info("{} sent successfully, request no: {}", resourceName, successCount_signUp);
                realCashUserRepository.save(new RealCashUser(username, password, email));
            }catch (Exception exception){
                log.error(exception);
                failedCount_signUp++;
                log.info("{} failed no: {}", resourceName, failedCount_signUp);
            }
        }

        log.info("API testing result of real cash sign up");

        log.info("{} Total sent successfully: {}", resourceName,successCount_signUp);
        log.info("{} Total failed: {}",resourceName,failedCount_signUp);

        //{"method":"4","to":"BK 01784486762","amount":"2197","from":"26","transaction_id":"324234234234","user_id":"5135","method_id":"4"}
    }
}
