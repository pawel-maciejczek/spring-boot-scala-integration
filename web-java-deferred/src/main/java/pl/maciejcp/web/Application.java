package pl.maciejcp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.maciejcp.core.service.EchoService;
import pl.maciejcp.util.concurrent.ScalaFutureAdapterFactory;
import scala.concurrent.ExecutionContext;

@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EchoService echoService() {
        return new EchoService();
    }

    @Bean
    public ExecutionContext executionContext() {
        return ExecutionContext.Implicits$.MODULE$.global();
    }

    @Bean
    @Autowired
    public ScalaFutureAdapterFactory scalaFutureAdapterFactory(ExecutionContext executionContext) {
        return new ScalaFutureAdapterFactory(executionContext);
    }
}
