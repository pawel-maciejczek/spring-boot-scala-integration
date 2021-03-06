package pl.maciejcp.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam, RestController}
import org.springframework.web.context.request.async.DeferredResult
import pl.maciejcp.core.service.{EchoResponse, EchoService}
import pl.maciejcp.util.concurrent.FutureAdapter.convert

import scala.Option
import scala.collection.Map

@RequestMapping(Array("/echo"))
@RestController
class EchoController @Autowired()(service: EchoService) {

  private implicit val executionContext = scala.concurrent.ExecutionContext.global

  @RequestMapping(Array("/"))
  def echo(@RequestParam(required = false, name = "input") input: String): String = {
    service.echo(input)
  }

  @RequestMapping(Array("/future"))
  def echoFuture(@RequestParam(required = false, name = "input") input: String): DeferredResult[String] = {
    service.echoFuture(input)
  }

  @RequestMapping(Array("/response"))
  def echoResponse(@RequestParam(required = false, name = "input") input: String): EchoResponse = {
    new EchoResponse(input, Option.apply(input))
    service.echoResponse(input)
  }

  @RequestMapping(Array("/responseCollection"))
  def echoResponseCollection(@RequestParam(required = false, name = "input") input: String): Map[String, Object] = {
    service.echoResponseCollection(input)
  }

  @RequestMapping(Array("/responseFuture"))
  def echoFutureResponse(@RequestParam(required = false, name = "input") input: String): DeferredResult[EchoResponse] = {
    service.echoFutureResponse(input)
  }

  @RequestMapping(Array("/failedFuture"))
  def failedFuture(@RequestParam(required = false, name = "input") input: String): DeferredResult[String] = {
    service.failedEcho(input)
  }
}
