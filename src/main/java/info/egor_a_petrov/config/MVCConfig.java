package info.egor_a_petrov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories(basePackages = {"info.egor_a_petrov.domain.repositories"})
@PropertySource("classpath:yandex.properties")
public class MVCConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

