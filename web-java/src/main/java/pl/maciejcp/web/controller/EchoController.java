package pl.maciejcp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejcp.core.service.EchoResponse;
import pl.maciejcp.core.service.JavaFutureEchoService;
import scala.Option;
import scala.collection.Map;

import java.util.concurrent.ExecutionException;

@RequestMapping("/echo")
@RestController
public class EchoController {
    private final JavaFutureEchoService service;

    @Autowired
    public EchoController(JavaFutureEchoService echoService) {
        this.service = echoService;
    }

    //    @Async
    @RequestMapping("/")
    public String echo(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echo(input);
    }

    @RequestMapping("/future")
    public String echoFuture(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoFuture(input).get();
    }

    @RequestMapping("/response")
    public EchoResponse echoResponse(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        new EchoResponse(input, Option.apply(input));
        return service.echoResponse(input);
    }

    @RequestMapping("/responseCollection")
    public Map<String, Object> echoResponseCollection(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoResponseCollection(input);
    }

    @RequestMapping("/responseFuture")
    public EchoResponse echoFutureResponse(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoFutureResponse(input).get();
    }

    @RequestMapping("/failedFuture")
    public String failedFuture(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.failedEcho(input).get();
    }

}