package si.kuharskimojster.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("si.kuharskimojster")
@EnableJpaRepositories(basePackages = "si.kuharskimojster.repositories")
@SpringBootApplication
public class ApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


}
