package at.refugeescode.Trickster.cnfigeration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Starter {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
