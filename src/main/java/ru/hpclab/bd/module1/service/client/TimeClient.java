package ru.hpclab.bd.module1.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A client class for interacting with an external time service.
 */
@Component
public class TimeClient {
    private final RestTemplate restTemplate;

    /**
     * Constructs a TimeClient instance.
     *
     * @param restTemplateBuilder The RestTemplateBuilder for creating a RestTemplate instance.
     * @param timeUrl             The URL of the external time service, specified in application properties.
     */
    public TimeClient(final RestTemplateBuilder restTemplateBuilder,
                      @Value("${integration.time.url}") final String timeUrl) {
        this.restTemplate = restTemplateBuilder
                .uriTemplateHandler(new RootUriTemplateHandler(timeUrl))
                .build();
    }

    /**
     * Retrieves the current local time from the external time service.
     *
     * @return The current local time as a LocalDateTime object.
     */
    public LocalDateTime getLocal() {
        ResponseEntity<TimeInfo> response = restTemplate.getForEntity("/", TimeInfo.class);
        return Objects.requireNonNull(response.getBody()).getDatetime().toLocalDateTime();
    }
}
