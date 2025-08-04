package com.ruoyi.system.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIService {

    private static final String API_URL = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String API_KEY = "sk-kdcgdkgkyqgypllksfxhthgrgnoarvzjexpkkqzoejiyqdwb"; // ✅ 请替换成你的真实密钥

    public String getAISuggestion(String resourceName, String timeRange) {
        RestTemplate restTemplate = new RestTemplate();

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        // 请求体
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "我预约了 " + resourceName + "，时间是 " + timeRange + "，请为我提供健身训练建议。");

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
}
