package pl.maciejcp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import pl.maciejcp.core.service.EchoResponse;
import pl.maciejcp.core.service.EchoService;
import pl.maciejcp.util.concurrent.ScalaFutureAdapterFactory;
import scala.Option;
import scala.collection.Map;

import java.util.concurrent.ExecutionException;

@RequestMapping("/echo")
@RestController
public class EchoController {
    private final EchoService service;
    private final ScalaFutureAdapterFactory futureAdapterFactory;

    @Autowired
    public EchoController(EchoService echoService, ScalaFutureAdapterFactory futureAdapterFactory) {
        this.service = echoService;
        this.futureAdapterFactory = futureAdapterFactory;
    }

    //    @Async
    @RequestMapping("/")
    public String echo(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echo(input);
    }

    @RequestMapping("/future")
    public DeferredResult<String> echoFuture(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return futureAdapterFactory.convert(service.echoFuture(input));
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
    public DeferredResult<EchoResponse> echoFutureResponse(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return futureAdapterFactory.convert(service.echoFutureResponse(input));
    }

}