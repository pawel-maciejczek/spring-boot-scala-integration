package pl.maciejcp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejcp.core.service.EchoResponse;
import pl.maciejcp.core.service.EchoService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class EchoController {
    private final EchoService service;

    @Autowired
    public EchoController(EchoService echoService) {
        this.service = echoService;
    }

    //    @Async
    @RequestMapping("/echo")
    public String echo(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echo(input);
    }

    @RequestMapping("/echoFuture")
    public Future<String> echoFuture(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoFuture(input);
    }

    @RequestMapping("/echoResponse")
    public EchoResponse echoResponse(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoResponse(input);
    }

    @RequestMapping("/echoFutureResponse")
    public Future<EchoResponse> echoFutureResponse(@RequestParam(required = false, name = "input") String input) throws ExecutionException, InterruptedException {
        return service.echoFutureResponse(input);
    }
}
