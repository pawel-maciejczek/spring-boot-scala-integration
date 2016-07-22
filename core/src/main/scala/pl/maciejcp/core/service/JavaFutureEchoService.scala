package pl.maciejcp.core.service

import pl.maciejcp.core.util.concurrent.JavaFutureConverter.{JavaFuture, convert}

import scala.concurrent.Future

class JavaFutureEchoService(echoService: EchoService) {

  def echo(input: String): String = {
    echoService.echo(input)
  }

  def echoFuture(input: String): JavaFuture[String] = {
    echoService.echoFuture(input)
  }

  def echoResponse(input: String): EchoResponse = {
    echoService.echoResponse(input)
  }

  def echoResponseCollection(input: String): Map[String, Object] = {
    echoService.echoResponseCollection(input)
  }

  def echoFutureResponse(input: String): JavaFuture[EchoResponse] = {
    Future.successful(EchoResponse(input, Option(input)))
  }

  def failedEcho(input: String): JavaFuture[String] = {
    Future.failed(new IllegalArgumentException(input))
  }
}
