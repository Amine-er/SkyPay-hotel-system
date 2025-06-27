package ma.errabi.booking.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookingConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder()
                .group("bookins")
                .pathsToMatch("/api/bookings/**")
                .build();
    }
}
