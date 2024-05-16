package kh.em.albaya.message.service;

import org.springframework.beans.factory.annotation.Value;

public class MessageService {
    private final SmsCertification   smsCertification;

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;
}
