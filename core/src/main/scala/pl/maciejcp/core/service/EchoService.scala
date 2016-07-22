package pl.maciejcp.core.service

import scala.concurrent.Future

case class EchoResponse(input: String, opt: Option[String])

class EchoService {
  def echo(input: String): String = {
    input
  }

  def echoFuture(input: String): Future[String] = {
    Future.successful(input)
  }

  def echoResponse(input: String): EchoResponse = {
    val response = EchoResponse(input, Option(input))
    response
  }

  def echoResponseCollection(input: String): Map[String, Object] = {
    val response = echoResponse(input)
    Map(
      "response" -> response,
      "list" -> List(response),
      "set" -> Set(response),
      "option" -> Option(input),
      "emptyOption" -> None
    )
  }

  def echoFutureResponse(input: String): Future[EchoResponse] = {
    Future.successful(EchoResponse(input, Option(input)))
  }

  def failedEcho(input: String): Future[String] = {
    Future.failed(new IllegalArgumentException(input))
  }
}



