package pl.maciejcp.core.service

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

case class EchoResponse(input: String, opt: Option[String])

class EchoService {
  def echo(input: String): String = {
    input
  }
  def echoFuture(input: String): java.util.concurrent.Future[String] = {
    Future.successful(input)
  }

  def echoResponse(input: String): EchoResponse = {
    EchoResponse(input, Option(input))
  }
  def echoFutureResponse(input: String): java.util.concurrent.Future[EchoResponse] = {
    Future.successful(EchoResponse(input, Option(input)))
  }

  def failedEcho(input: String): java.util.concurrent.Future[String] = {
    Future.failed(new IllegalArgumentException(input))
  }

  implicit def convert[T](x: Future[T]): java.util.concurrent.Future[T] = {
    new java.util.concurrent.Future[T] {
      override def isCancelled: Boolean = {
        false
      }

      override def get(): T = Await.result(x, Duration.Inf)

      override def get(timeout: Long, unit: TimeUnit): T = Await.result(x, Duration.create(timeout, unit))

      override def cancel(mayInterruptIfRunning: Boolean): Boolean = throw new UnsupportedOperationException

      override def isDone: Boolean = x.isCompleted
    }
  }
}

