package pl.maciejcp.util.concurrent

import org.springframework.web.context.request.async.DeferredResult

import scala.concurrent.{ExecutionContext, Future}

object FutureAdapter {
  implicit def convert[T](target: Future[T])(implicit executionContext: ExecutionContext): DeferredResult[T] = {
    new FutureAdapter(target)
  }
}

class FutureAdapter[T](target: Future[T])(implicit executionContext: ExecutionContext) extends DeferredResult[T] {
  target.onSuccess {
    case result => this.setResult(result)
  }
  target.onFailure {
    case error => this.setErrorResult(error)
  }
}
