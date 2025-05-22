package peaksoft.java15.demo111.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public void fetchAndLogExternalObjects() {
        String url = "https://api.restful-api.dev/objects";
        try {
            String response = restTemplate.getForObject(url, String.class);
            log.info("External API response: {}", response);
        } catch (Exception e) {
            log.error("Error during external API request: {}", e.getMessage(), e);
        }
    }
}
