package pl.maciejcp.core.util.concurrent

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object JavaFutureConverter {
  type JavaFuture[T] = java.util.concurrent.Future[T]

  implicit def convert[T](future: Future[T]): JavaFuture[T] = {
    new JavaFutureAdapter[T](future)
  }

  private class JavaFutureAdapter[T](future: Future[T]) extends JavaFuture[T] {
    override def isCancelled: Boolean = {
      false
    }

    override def get(): T = Await.result(future, Duration.Inf)

    override def get(timeout: Long, unit: TimeUnit): T = Await.result(future, Duration.create(timeout, unit))

    override def cancel(mayInterruptIfRunning: Boolean): Boolean = throw new UnsupportedOperationException

    override def isDone: Boolean = future.isCompleted
  }

}
