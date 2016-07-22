package pl.maciejcp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ScalaJacksonMapper {
    @Bean
    public ObjectMapper buildScalaMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.registerModule(new DefaultScalaModule());
    }
}
