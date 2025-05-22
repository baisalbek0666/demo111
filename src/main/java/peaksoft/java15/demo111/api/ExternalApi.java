package peaksoft.java15.demo111.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.java15.demo111.service.impl.ExternalApiService;

@RestController
@RequiredArgsConstructor
public class ExternalApi {

    private final ExternalApiService externalApiService;

    @GetMapping("/external-api")
    public String callApi() {
        externalApiService.fetchAndLogExternalObjects();
        return "Check logs for external API response.";
    }
}
