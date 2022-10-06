package com.redcutlery.thingder.module.naver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redcutlery.thingder.exception.BaseException;
import com.redcutlery.thingder.module.naver.dto.SMSRequest.MessagesItem;
import com.redcutlery.thingder.module.naver.dto.SMSRequest.SMSRequest;
import com.redcutlery.thingder.module.naver.dto.SMSRespose.SMSResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class NaverService {
    private final ObjectMapper objectMapper;
    @Value("${naver.api.access_key_id}")
    private String accessKey;
    @Value("${naver.api.secret_key}")
    private String secretKey;
    @Value("${naver.sms.service_id}")
    private String smsServiceId;
    @Value("${naver.sms.calling_number}")
    private String callingNumber;

    public SMSResponse sendSms(List<MessagesItem> messages) {
        try {
            var url = "https://sens.apigw.ntruss.com";
            var uri = "/sms/v2/services/" + smsServiceId + "/messages";
            var time = Long.toString(System.currentTimeMillis());

            var smsRequest = new SMSRequest("SMS", callingNumber, "밥.");
            smsRequest.setMessages(messages);

            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-ncp-apigw-timestamp", time);
            headers.set("x-ncp-iam-access-key", accessKey);
            headers.set("x-ncp-apigw-signature-v2", getSignature(uri, time));

            var body = objectMapper.writeValueAsString(smsRequest);
            var httpEntity = new HttpEntity<>(body, headers);

            var restTemplate = new RestTemplate();

            return restTemplate.postForObject(new URI(url + uri), httpEntity, SMSResponse.class);
        } catch (Exception e) {
            throw new BaseException.ServerError("메시지 전송에 실패했습니다.");
        }
    }

    public String getSignature(String uri, String time) throws NoSuchAlgorithmException, InvalidKeyException {
        var message =
                "POST " + uri + "\n" +
                        time + "\n" +
                        accessKey;

        var signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        var mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        var rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(rawHmac);
    }
}
