package com.ruoyi.system.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    // TODO :列出todo

    @Value("${app.ai.url}")
    private String API_URL;

    @Value("${app.ai.key}")
    private String API_KEY;

    // 时间转换（加这段函数）
    public String convertTimeRangeToChinese(String timeRange) {
        if ("00:08:00 - 00:10:00".equals(timeRange)) {
            return "上午八点到十点";
        } else if ("00:10:00 - 00:12:00".equals(timeRange)) {
            return "上午十点到十二点";
        }
        return timeRange;
    }

    public String getAISuggestion(String resourceName, String timeRange) {
        RestTemplate restTemplate = new RestTemplate();

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        // 请求体
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        // 主方法中：
        String chineseTime = convertTimeRangeToChinese(timeRange);
        message.put("content", "我预约了 " + resourceName + "，时间是 " + chineseTime + "，请用100字以内提供健身训练建议。");


        Map<String, Object> body = new HashMap<>();
        body.put("model", "deepseek-ai/DeepSeek-R1");
        body.put("messages", Collections.singletonList(message));
        body.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("choices")) {
                List choices = (List) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map choice = (Map) choices.get(0);
                    Map messageObj = (Map) choice.get("message");
                    return (String) messageObj.get("content");
                }
            }
            return "AI 暂无建议";

        } catch (Exception e) {
            e.printStackTrace();
            return "AI 接口调用失败：" + e.getMessage();
        }
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }
}
