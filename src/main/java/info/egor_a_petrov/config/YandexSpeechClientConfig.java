package info.egor_a_petrov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:yandex.properties")
public class YandexSpeechClientConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

