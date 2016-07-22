package pl.maciejcp.web

;

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component;

@Component
class ScalaJacksonMapper {
  @Bean
  def buildScalaMapper(): ObjectMapper = {
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
  }
}
