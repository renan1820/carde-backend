package br.com.carde.api.infrastructure.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class FcmService {

    private static final String FCM_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

    @Value("${firebase.project-id}")
    private String projectId;

    @Value("${firebase.service-account-json}")
    private String serviceAccountJson;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendToTopic(String topic, String title, String body) {
        try {
            String accessToken = getAccessToken();
            String url = "https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send";

            String payload = """
                    {
                      "message": {
                        "topic": "%s",
                        "notification": {
                          "title": "%s",
                          "body": "%s"
                        },
                        "android": {
                          "notification": {
                            "channel_id": "carde_default"
                          }
                        }
                      }
                    }
                    """.formatted(topic, escapeJson(title), escapeJson(body));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            restTemplate.postForObject(url, new HttpEntity<>(payload, headers), String.class);
            log.info("FCM notification sent to topic '{}': {}", topic, title);

        } catch (Exception e) {
            log.error("Failed to send FCM notification: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao enviar notificação push", e);
        }
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8)))
                .createScoped(List.of(FCM_SCOPE));
        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
