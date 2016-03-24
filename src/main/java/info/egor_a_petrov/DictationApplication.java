package info.egor_a_petrov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"info.egor_a_petrov.domain.repositories"})
public class DictationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictationApplication.class, args);
	}
}
