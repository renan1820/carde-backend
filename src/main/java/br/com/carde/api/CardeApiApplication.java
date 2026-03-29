package br.com.carde.api;

import br.com.carde.api.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class CardeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardeApiApplication.class, args);
    }
}
