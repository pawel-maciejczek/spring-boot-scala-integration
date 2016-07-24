package pl.maciejcp.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import pl.maciejcp.core.service.EchoService;


object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application])
  }

}

@EnableAsync
@SpringBootApplication
class Application {

  @Bean
  def echoService(): EchoService = {
    new EchoService()
  }
}
