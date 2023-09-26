package com.sshmarket.auth.auth.application.port.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.sshmarket.auth.auth.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OauthConnector {

    private final Environment environment;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String code) {
        String clientId = environment.getProperty("oauth2.google.clientId");
        String clientSecret = environment.getProperty("oauth2.google.clientSecret");
        String redirectUri = environment.getProperty("oauth2.google.redirectUri");
        String tokenUri = environment.getProperty("oauth2.google.tokenUri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JsonNode accessTokenNode = null;
        try {
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
            ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(tokenUri, HttpMethod.POST, httpEntity, JsonNode.class);
            accessTokenNode = responseEntity.getBody();
        } catch (Exception e) {
            throw new BusinessException("계정 연결 중 문제가 발생했습니다.");
        }
        return accessTokenNode.get("access_token").asText();
    }

    public JsonNode getMemberResource(String accessToken) {
        String resourceUri = environment.getProperty("oauth2.google.resourceUri");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer" + accessToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        try {
            return restTemplate.exchange(resourceUri, HttpMethod.GET, httpEntity, JsonNode.class).getBody();    
        } catch (Exception e) {
            throw new BusinessException("계정 정보를 불러오는 중 문제가 발생했습니다.");
        }
        
    }

}
