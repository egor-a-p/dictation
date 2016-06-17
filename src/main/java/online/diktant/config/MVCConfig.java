package online.diktant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories(basePackages = {"info.egor_a_petrov.repository"})
@PropertySource("classpath:yandex.properties")
public class MVCConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

