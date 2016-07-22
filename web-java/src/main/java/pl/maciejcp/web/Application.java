package pl.maciejcp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.maciejcp.core.service.EchoService;
import pl.maciejcp.core.service.JavaFutureEchoService;

@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JavaFutureEchoService echoService() {
        return new JavaFutureEchoService(new EchoService());
    }
}
